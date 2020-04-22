package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.FuelType;

import java.util.List;

public interface FuelTypeDao extends Dao<FuelType> {

    public List<String> getAllName() ;
}
