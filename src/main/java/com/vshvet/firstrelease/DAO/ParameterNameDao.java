package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;

import java.util.List;

public interface ParameterNameDao extends Dao<ParameterNames> {
    List<ParameterNames> getAllTreeRootName();

    List<ParameterNames> getAllParameterSizeName();

    ParameterNames findByName(String name);

    Integer getMaxId();

    List<ParameterNames> getPagination(PaginationDataRequest request);

    Long getCountResults(PaginationDataRequest request);

    long getCountResultsParamSize(PaginationDataRequest request);
}
