package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.AutomobileEngineResponse;

import java.util.List;

public interface AutomobileEngineService {
    List<AutomobileEngineResponse> findByParam(EngineRequest engineRequest);
}
