package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.AutoModelDao;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.AutoModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoModelServiceImpl implements AutoModelService {

    @Autowired
    private AutoModelDao autoModelDao;

    @Override
    public List<AutoModel> getAutoModel() {
        autoModelDao.openCurrentSessionwithTransaction();
        List<AutoModel> autoModels = autoModelDao.getAll();
        autoModelDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

    @Override
    public List<String> getAllNameOfModel() {
        autoModelDao.openCurrentSessionwithTransaction();
        List<String> models = autoModelDao.getAllNameOfModel();
        autoModelDao.closeCurrentSessionwithTransaction();
        return models;
    }

    @Override
    public AutoModel findById(int id) {
        autoModelDao.openCurrentSessionwithTransaction();
        AutoModel model = null;
        model = autoModelDao.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id : " + id));
        autoModelDao.closeCurrentSessionwithTransaction();
        return model;
    }
}
