package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.AutoModelDao;
import com.vshvet.firstrelease.Entity.AutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoModelService {

    @Autowired
    private AutoModelDao autoModelDao;

    public List<AutoModel> getAutoModel(){
        autoModelDao.openCurrentSessionwithTransaction();
        List<AutoModel> autoModels =autoModelDao.getAll();
        autoModelDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

    public List<String> getAllNameOfModel(){
        autoModelDao.openCurrentSessionwithTransaction();
        List<String> models =autoModelDao.getAllNameOfModel();
        autoModelDao.closeCurrentSessionwithTransaction();
        return models;
    }

    public AutoModel findById(int i) {
        autoModelDao.openCurrentSessionwithTransaction();
        AutoModel model =autoModelDao.findById(i).get();
        autoModelDao.closeCurrentSessionwithTransaction();
        return model;
    }
}
