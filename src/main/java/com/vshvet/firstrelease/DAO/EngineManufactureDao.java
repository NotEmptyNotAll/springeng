package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;

import java.util.List;
import java.util.Set;

public interface EngineManufactureDao extends Dao<EngineManufacturer> {
    List<String> getAllName();

    EngineManufacturer findByName(String name);

    Set<EngineManufacturer> getCroppedByParamName(EngineRequest engineRequest);

    List<EngineManufacturer> getPagination(PaginationDataRequest request);

    Long getCountResults(PaginationDataRequest request);
}
