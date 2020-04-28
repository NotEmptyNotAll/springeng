package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.AutomobileEngineResponse;
import com.vshvet.firstrelease.payload.Response.ElementsResponse;

import java.util.List;
import java.util.Set;

public interface ElementsService {
    ElementsResponse getElements(Integer id);

    Set<Integer> getParentElemId(EngineRequest request);

    List<AutomobileEngineResponse> getParentElements(EngineRequest request);
}
