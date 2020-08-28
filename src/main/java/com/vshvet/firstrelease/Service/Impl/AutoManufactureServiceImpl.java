package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.AutoManufactureDao;
import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.Status;
import com.vshvet.firstrelease.Service.AutoManufactureService;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
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
    @Transactional
    public AutoManufacture findById(Integer id) {
        return autoManufactureDao.findById(id).get();
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
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

    @Override
    public List<DataByIdResponse> getCroppedData(EngineRequest engine) {
        return new ArrayList<DataByIdResponse>() {{
            autoManufactureDao.getCroppedByParamName(engine).forEach(elem -> {
                add(new DataByIdResponse(elem.getManufactureName(),
                        elem.getId()));
            });
        }};
    }
}
