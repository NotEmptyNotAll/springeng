package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.EngineManufactureDao;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Service.EngineManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineManufactureServiceImlp implements EngineManufactureService {

    @Autowired
    private EngineManufactureDao engineManufactureDao;

    //get from database all name of engine manufacture
    @Override
    public List<String> getAllName() {
        engineManufactureDao.openCurrentSessionwithTransaction();
        List<String> allName = engineManufactureDao.getAllName();
        engineManufactureDao.closeCurrentSessionwithTransaction();
        return allName;
    }

    @Override
    public List<EngineManufacturer> getAllEngineManufacture() {
        engineManufactureDao.openCurrentSessionwithTransaction();
        List<EngineManufacturer> autoModels = engineManufactureDao.getAll();
        engineManufactureDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

}
