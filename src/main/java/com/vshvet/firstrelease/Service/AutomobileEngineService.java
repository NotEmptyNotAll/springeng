package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.AutoDataResponse;
import com.vshvet.firstrelease.payload.Response.AutoEngineResponse;
import com.vshvet.firstrelease.payload.Response.AutomobileEngineResponse;
import com.vshvet.firstrelease.payload.Response.DefaultDataResponse;

import java.util.List;

public interface AutomobileEngineService {
    List<AutomobileEngineResponse> findByParam(EngineRequest engineRequest);

    List<AutoEngineResponse> findByParamForUpdate(EngineRequest engineRequest);

    Boolean update(List<UpdateAutoEngineRequest> updateData);


    String save(SaveAutoEngineRequest saveData);

    String getNameAuto(Integer id);

    AutomobileEngine findById(Integer id);

    AutoEngineResponse getById(Integer id);

    List<AutomobileEngine> getAllAutoEngine();

    List<AutoDataResponse> getAllAuto();

    void imprt(ImprtAutoEngineRequest imprtData);
}
