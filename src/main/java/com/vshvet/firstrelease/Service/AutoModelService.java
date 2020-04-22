package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.AutoModelDao;
import com.vshvet.firstrelease.DAO.AutoModelDaoImpl;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoModelService {

    @Autowired
    private AutoModelDao autoModelDao;

    public List<AutoModel> getAutoModel() {
        autoModelDao.openCurrentSessionwithTransaction();
        List<AutoModel> autoModels = autoModelDao.getAll();
        autoModelDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

    public List<String> getAllNameOfModel() {
        autoModelDao.openCurrentSessionwithTransaction();
        List<String> models = autoModelDao.getAllNameOfModel();
        autoModelDao.closeCurrentSessionwithTransaction();
        return models;
    }

    public AutoModel findById(int id) {
        autoModelDao.openCurrentSessionwithTransaction();
        AutoModel model = null;
        model = autoModelDao.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id : " + id));
        autoModelDao.closeCurrentSessionwithTransaction();
        return model;
    }
}
