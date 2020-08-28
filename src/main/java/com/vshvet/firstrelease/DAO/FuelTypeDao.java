package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;

import java.util.List;
import java.util.Set;

public interface FuelTypeDao extends Dao<FuelType> {

    Set<FuelType> getCroppedByParamType(EngineRequest engineRequest);

    FuelType findByName(String name);

    List<FuelType> getPagination(PaginationDataRequest request);

    Long getCountResults(PaginationDataRequest request);

    List<String> getAllName() ;
}
