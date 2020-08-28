package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;

public interface EngineManufactureService {
    List<String> getAllName();

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