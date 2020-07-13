package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.payload.Request.EngineRequest;

import java.util.List;
import java.util.Set;

public interface FuelTypeDao extends Dao<FuelType> {

    Set<FuelType> getCroppedByParamType(EngineRequest engineRequest);

    List<String> getAllName() ;
}
