package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Payload.Request.EngineRequest;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;

import java.util.List;

public interface AutoManufactureDao extends Dao<AutoManufacture> {

    List<AutoManufacture> getCroppedByParamName(EngineRequest engineRequest);

    AutoManufacture findByName(String name);

    List<String> getAllNameOfManufacture();

    List<AutoManufacture> getPagination(PaginationDataRequest paginationDataRequest);

    Long getCountResults(PaginationDataRequest request);

}
