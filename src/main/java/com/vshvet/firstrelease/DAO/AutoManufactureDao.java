package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;

import java.util.List;
import java.util.Set;

public interface AutoManufactureDao extends Dao<AutoManufacture> {

    List<AutoManufacture> getCroppedByParamName(EngineRequest engineRequest);

    AutoManufacture findByName(String name);

    List<String> getAllNameOfManufacture();

    List<AutoManufacture> getPagination(PaginationDataRequest paginationDataRequest);

    Long getCountResults(PaginationDataRequest request);

}
