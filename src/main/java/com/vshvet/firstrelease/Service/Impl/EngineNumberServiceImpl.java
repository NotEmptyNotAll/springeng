package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.EngineNumberDao;
import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.EngineNumberService;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.EngineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EngineNumberServiceImpl implements EngineNumberService {

    @Autowired
    private EngineNumberDao engineNumberDao;


    @Override
    @Transactional
    public List<String> getAllNumber() {
        List<String> list = engineNumberDao.getAllName();
        return list;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        EngineNumber engineNumber = engineNumberDao.findById(id).get();
        this.engineNumberDao.delete(engineNumber);
        return null;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getCroppedData(EngineRequest engineRequest) {
        return new ArrayList<DataByIdResponse>() {{
            engineNumberDao.getCroppedByParamName(engineRequest).forEach(elem -> {
                add(new DataByIdResponse(elem.getNumber(),
                        elem.getId(),elem.getStatus().getStatus()));
            });
        }};
    }

    @Override
    @Transactional
    public String save(SaveTwoDataRequest saveData) {
        try {
            EngineNumber engineNumber = new EngineNumber();
            engineNumber.setStatus(new Status(saveData.getStatus()));
            engineNumber.setNumber(saveData.getSaveData_primary());
            engineNumber.setEngineByEngineFk(new AutomobileEngine(Integer.parseInt(saveData.getSaveData_secondary())));
            engineNumberDao.save(engineNumber);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public List<EngineNumber> getAllEngineNumber() {
        List<EngineNumber> all = engineNumberDao.getAll();
        return all;
    }

    @Override
    @Transactional
    public Boolean update(UpdateTwoDataRequest updateData) {
        try {
            EngineNumber newEngineNumber = engineNumberDao.findById(updateData.getObjToBeChanged()).get();
            EngineNumber oldEngineNumber = new EngineNumber(newEngineNumber);
            newEngineNumber.setStatus(new Status(updateData.getStatus()));
            newEngineNumber.setNumber(updateData.getSaveData_primary());
            newEngineNumber.setEngineByEngineFk(new AutomobileEngine(Integer.parseInt(updateData.getSaveData_secondary())));
            oldEngineNumber.setDate(new Date(new java.util.Date().getTime()));
            engineNumberDao.update(newEngineNumber,oldEngineNumber);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            engineNumberDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getNumber(),
                        elem.getId(),elem.getStatus().getStatus()));
            });
        }};
        return all;
    }

    @Override
    @Transactional
    public EngineResponse getAutoEngByNumber(EngineRequest engineRequest) {
        EngineResponse automobileEngineResponse = new EngineResponse(engineNumberDao
                .getAutoEngByNum(engineRequest.getNumberEng()));
        return automobileEngineResponse;
    }


}
