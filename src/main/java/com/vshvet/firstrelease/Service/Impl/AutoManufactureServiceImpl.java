package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.AutoManufactureDao;
import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.Status;
import com.vshvet.firstrelease.Service.AutoManufactureService;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.AutoDataResponse;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AutoManufactureServiceImpl implements AutoManufactureService {

    @Autowired
    private AutoManufactureDao autoManufactureDao;


    @Override
    public AutoManufacture findByName(String name) {
        return autoManufactureDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataByIdResponse> getPaginationData(PaginationDataRequest request) {
        return new ArrayList<DataByIdResponse>(){{
            autoManufactureDao.getPagination(request).forEach(item->{
                add(new DataByIdResponse(item.getManufactureName(),item.getId(),item.getStatus().getStatus()));
            });
        }};
    }

    @Override
    public Integer getNumberOfPage(PaginationDataRequest request) {
        return (int) Math.ceil(Double.valueOf(autoManufactureDao
                .getCountResults(request)) / Double.valueOf(request.getPageSize()));
    }

    @Override
    public void save(AutoManufacture autoManufacture) {
        try {
            autoManufactureDao.save(autoManufacture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        AutoManufacture autoManufacture = autoManufactureDao.findById(id).get();
        List<AutomobileEngine> automobileEngines= autoManufacture.getAutomobileEnginesById()
                .stream().filter(item->item.getDate()==null).collect(Collectors.toList());
        if (automobileEngines.size()==0) {
            this.autoManufactureDao.delete(autoManufacture);
            return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
               automobileEngines.forEach(elem ->
                        add(new DataByIdResponse(elem.getEngineByEngineFk().getEngineType(), elem.getId())));
            }};
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AutoManufacture findById(Integer id) {
        return autoManufactureDao.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllManufacture() {
        List<String> allName = autoManufactureDao.getAllNameOfManufacture();
        return allName;
    }

    @Override
    @Transactional
    public Boolean update(UpdateDataRequest updateData) {
        try {
            AutoManufacture newAutoManufacture = autoManufactureDao.findById(updateData.getObjToBeChanged()).get();
            AutoManufacture oldAutoManufacture = new AutoManufacture(newAutoManufacture);
            newAutoManufacture.setManufactureName(updateData.getUpdateData());
            newAutoManufacture.setId(updateData.getObjToBeChanged());
            newAutoManufacture.setStatus(new Status(updateData.getStatus()));
            oldAutoManufacture.setDate(new Date(new java.util.Date().getTime()));
            autoManufactureDao.update(newAutoManufacture, oldAutoManufacture);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            autoManufactureDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getManufactureName(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};
        return all;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutoManufacture> getAllAutoManufacture() {
        List<AutoManufacture> all = autoManufactureDao.getAll();
        return all;
    }

    @Override
    @Transactional
    public String save(SaveDataRequest saveData) {
        try {
            AutoManufacture autoManufacture = new AutoManufacture();
            autoManufacture.setManufactureName(saveData.getSaveData());
            autoManufacture.setStatus(new Status(saveData.getStatus()));
            autoManufactureDao.save(autoManufacture);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    public void imprt(ImprtDataRequest imprtData) {
        imprtData.getList().forEach(this::save);
    }

    @Transactional(readOnly = true)
    public List<DataByIdResponse> getCroppedData(EngineRequest engine) {
        return new ArrayList<DataByIdResponse>() {{
            autoManufactureDao.getCroppedByParamName(engine).forEach(elem -> {
                add(new DataByIdResponse(elem.getManufactureName(),
                        elem.getId()));
            });
        }};
    }
}
