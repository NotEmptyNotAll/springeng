package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.payload.Response.AutoDataResponse;

import java.util.List;
import java.util.Set;

public interface EngineDao extends Dao<Engine> {
    List<String> getAllType();

    Engine findByName(String name);

    Set<Engine> getCroppedType(EngineRequest engineRequest);

    List<Engine> getPaginationData(PaginationDataRequest request);

    Long getNumberOfPage(PaginationDataRequest request);
}
