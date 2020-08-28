package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ParametersPageRequest;
import com.vshvet.firstrelease.payload.Response.DefaultDataResponse;

import java.util.List;

public interface AutomobileEngineDao extends Dao<AutomobileEngine> {

    List<AutomobileEngine> getAutoByParam(EngineRequest engineRequest);

    AutomobileEngine findByNames(String autoModel, String engineType, String autoManuf, String years);

    Long getCountResults(ParametersPageRequest request);

    List<AutomobileEngine> getPaginationAutoEng(ParametersPageRequest request);

}