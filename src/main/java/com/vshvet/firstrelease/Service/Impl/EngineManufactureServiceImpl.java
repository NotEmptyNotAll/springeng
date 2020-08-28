package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.EngineManufactureDao;
import com.vshvet.firstrelease.Entity.*;

import com.vshvet.firstrelease.Service.EngineManufactureService;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EngineManufactureServiceImpl implements EngineManufactureService {

    @Autowired
    private EngineManufactureDao engineManufactureDao;

    //get from database all name of engine manufacture
    @Override
    @Transactional
    public List<String> getAllName() {
        List<String> allName = engineManufactureDao.getAllName();
        return allName;
    }

    @Override
    public EngineManufacturer findByName(String name) {
        return engineManufactureDao.findByName(name);
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        EngineManufacturer engineManufacturer = engineManufactureDao.findById(id).get();
        List<Engine> engines = engineManufacturer.getEnginesById()
                .stream().filter(item -> item.getDate() == null).collect(Collectors.toList());
        if (engines.size() == 0) {
            this.engineManufactureDao.delete(engineManufacturer);
            return null;
        } else {
            return new ArrayList<DataByIdResponse>() {{
                engines.forEach(elem ->
                        add(new DataByIdResponse(elem.getEngineType(), elem.getId())));
            }};
        }
    }

    @Override
    public void save(EngineManufacturer engineManufacturer) {
        try {
            engineManufactureDao.save(engineManufacturer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            engineManufactureDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getNameManufacturer(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};
        return all;
    }

    @Override
    @Transactional
    public Boolean update(UpdateDataRequest updateData) {
        try {
            EngineManufacturer newEngineManufacturer = new EngineManufacturer();
            EngineManufacturer oldEngineManufacturer = new EngineManufacturer(newEngineManufacturer);
            newEngineManufacturer.setStatus(new Status(updateData.getStatus()));
            newEngineManufacturer.setNameManufacturer(updateData.getUpdateData());
            oldEngineManufacturer.setDate(new Date(new java.util.Date().getTime()));
            engineManufactureDao.update(newEngineManufacturer, oldEngineManufacturer);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getCroppedData(EngineRequest engineRequest) {
        return new ArrayList<DataByIdResponse>() {{
            engineManufactureDao.getCroppedByParamName(engineRequest).forEach(elem -> {
                add(new DataByIdResponse(elem.getNameManufacturer(),
                        elem.getId()));
            });
        }};
    }

    @Override
    @Transactional
    public String save(SaveDataRequest saveData) {
        try {
            EngineManufacturer engineManufacturer = new EngineManufacturer();
            engineManufacturer.setStatus(new Status(saveData.getStatus()));
            engineManufacturer.setNameManufacturer(saveData.getSaveData());
            engineManufactureDao.save(engineManufacturer);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public List<EngineManufacturer> getAllEngineManufacture() {
        List<EngineManufacturer> autoModels = engineManufactureDao.getAll();
        return autoModels;
    }

    @Override
    public void imprt(ImprtDataRequest imprtData) {
        imprtData.getList().forEach(this::save);
    }

}
