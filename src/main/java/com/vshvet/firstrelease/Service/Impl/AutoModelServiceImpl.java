package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.AutoModelDao;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.Status;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.AutoModelService;
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

@Service
public class AutoModelServiceImpl implements AutoModelService {

    @Autowired
    private AutoModelDao autoModelDao;

    @Override
    @Transactional
    public List<AutoModel> getAutoModel() {
        List<AutoModel> autoModels = autoModelDao.getAll();
        return autoModels;
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getCroppedData(EngineRequest engineRequest) {
        return new ArrayList<DataByIdResponse>() {{
            autoModelDao.getCroppedModel(engineRequest).forEach(elem -> {
                add(new DataByIdResponse(elem.getModelName(),
                        elem.getId(),elem.getStatus().getStatus()));
            });
        }};
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getDataByIdResponse() {
        try {
            List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
                autoModelDao.getAll().forEach(elem -> {
                    add(new DataByIdResponse(elem.getModelName(),
                            elem.getId(),elem.getStatus().getStatus()));
                });
            }};
            return all;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
        }
    }

    @Override
    @Transactional
    public Boolean update(UpdateDataRequest updateData) {
        try {

            AutoModel newAutoModel = autoModelDao.findById(updateData.getObjToBeChanged()).get();
            AutoModel oldAutoModel = new AutoModel(newAutoModel);
            newAutoModel.setStatus(new Status(updateData.getStatus()));
            newAutoModel.setModelName(updateData.getUpdateData());
            newAutoModel.setId(updateData.getObjToBeChanged());
            autoModelDao.update(newAutoModel,oldAutoModel);
            oldAutoModel.setDate(new Date(new java.util.Date().getTime()));
            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }

    @Override
    @Transactional
    public String save(SaveDataRequest saveData) {
        try {

            AutoModel autoModel = new AutoModel();
            autoModel.setStatus(new Status(saveData.getStatus()));
            autoModel.setModelName(saveData.getSaveData());
            autoModelDao.save(autoModel);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public List<String> getAllNameOfModel() {
        List<String> models = autoModelDao.getAllNameOfModel();
        return models;
    }

    @Override
    @Transactional
    public AutoModel findById(int id) {

        AutoModel model = null;
        model = autoModelDao.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id : " + id));

        return model;
    }

    @Override
    public void imprt(ImprtDataRequest imprtData) {
        imprtData.getList().forEach(this::save);
    }
}
