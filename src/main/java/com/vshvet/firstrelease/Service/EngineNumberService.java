package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.EngineResponse;

import java.util.List;

public interface EngineNumberService {
    List<String> getAllNumber();

    EngineResponse getAutoEngByNumber(EngineRequest engineRequest);
}
