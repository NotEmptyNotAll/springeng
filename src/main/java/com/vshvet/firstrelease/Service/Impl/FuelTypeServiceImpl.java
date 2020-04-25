package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.FuelTypeDao;
import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeDao fuelTypeDao;

    @Override
    public List<String> getAllName() {
        fuelTypeDao.openCurrentSessionwithTransaction();
        List<String> autoModels = fuelTypeDao.getAllName();
        fuelTypeDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

    @Override
    public List<FuelType> getAllFuelType() {
        fuelTypeDao.openCurrentSessionwithTransaction();
        List<FuelType> autoModels = fuelTypeDao.getAll();
        fuelTypeDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

}
