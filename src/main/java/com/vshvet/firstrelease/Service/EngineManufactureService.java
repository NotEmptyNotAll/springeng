package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

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