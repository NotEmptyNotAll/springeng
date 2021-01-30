package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.Payload.Response.EngineDataResponse;
import com.vshvet.firstrelease.Payload.Response.FullEngineResponse;

import java.util.List;

public interface EngineService {

    Engine findById(int id);

    Integer fastSaveEngineData(FastAutoEngineSaveOrUpdateRequest parametersPageRequest);

    Boolean fastUpdateEngineData(FastAutoEngineSaveOrUpdateRequest parametersPageRequest);

    List<DataByIdResponse> delete(Integer id);

    List<String> getAllType();

    Engine findByName(String name);

    List<EngineDataResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);


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
