package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Payload.Request.EngineRequest;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.Payload.Request.ParametersPageRequest;

import java.util.List;

public interface AutomobileEngineDao extends Dao<AutomobileEngine> {

    List<AutomobileEngine> getAutoByParam(EngineRequest engineRequest);

    AutomobileEngine findByNames(String autoModel, String engineType, String autoManuf, String years);

    Long getCountResultsByParam(ParametersPageRequest request,List<AutomobileEngine> automobileEngineList);

    Integer getMaxId();


    List<AutomobileEngine> getPaginationAutoEngByParam(ParametersPageRequest request,List<AutomobileEngine> automobileEngineList);

    List<AutomobileEngine> getPaginationAutoEng(PaginationDataRequest paginationDataRequest);

    Long getCountResults(PaginationDataRequest request);

}