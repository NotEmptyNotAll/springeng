package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.DAO.FuelTypeDao;
import com.vshvet.firstrelease.DAO.FuelTypeDaoImpl;
import com.vshvet.firstrelease.Entity.FuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeService {

    @Autowired
    private FuelTypeDao fuelTypeDao;

    public List<String> getAllName() {
        fuelTypeDao.openCurrentSessionwithTransaction();
        List<String> autoModels = fuelTypeDao.getAllName();
        fuelTypeDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

    public List<FuelType> getAllFuelType() {
        fuelTypeDao.openCurrentSessionwithTransaction();
        List<FuelType> autoModels = fuelTypeDao.getAll();
        fuelTypeDao.closeCurrentSessionwithTransaction();
        return autoModels;
    }

}
