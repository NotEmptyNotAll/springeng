package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;
import java.util.Set;

public interface AutoModelDao extends Dao<AutoModel> {
    List<String> getAllNameOfModel();

    AutoModel findByName(String name);

    Set<AutoModel> getCroppedModel(EngineRequest engineRequest);

    List<AutoModel> getPagination(PaginationDataRequest request);

    Long getCountResults(PaginationDataRequest request);

}
