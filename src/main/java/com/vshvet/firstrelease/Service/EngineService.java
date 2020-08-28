package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ImprtOrUpdateEngineRequest;
import com.vshvet.firstrelease.payload.Request.SaveOrUpdateEngineRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.EngineDataResponse;
import com.vshvet.firstrelease.payload.Response.EngineResponse;
import com.vshvet.firstrelease.payload.Response.FullEngineResponse;

import java.util.List;
import java.util.Set;

public interface EngineService {
    Engine findById(int id);
    List<DataByIdResponse> delete(Integer id);

    List<String> getAllType();

    Engine findByName(String name);


    List<DataByIdResponse> getCroppedData(EngineRequest engineRequest);

    String save(SaveOrUpdateEngineRequest saveData);

    List<Engine> getAll();
    Boolean update(SaveOrUpdateEngineRequest updateData);


    List<EngineDataResponse> getDataByIdResponse();

    void update(Engine engine);

    void save(Engine engine) throws Exception;

    void delete(Engine engine) throws Exception;

    FullEngineResponse getEngineResponse(Integer id);

    void imprt(ImprtOrUpdateEngineRequest imprtData);
}
