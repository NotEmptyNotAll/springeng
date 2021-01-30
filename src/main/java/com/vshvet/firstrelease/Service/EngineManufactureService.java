package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;

public interface EngineManufactureService {
    List<String> getAllName();
    List<DataByIdResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);


    EngineManufacturer findByName(String name);

    List<DataByIdResponse> delete(Integer id);

    void save(EngineManufacturer engineManufacturer);


    List<DataByIdResponse> getDataByIdResponse();
    Boolean update(UpdateDataRequest updateData);


    List<DataByIdResponse> getCroppedData(EngineRequest engineRequest);

    String save(SaveDataRequest saveData);

    List<EngineManufacturer> getAllEngineManufacture();

    void imprt(ImprtDataRequest imprtData);
}