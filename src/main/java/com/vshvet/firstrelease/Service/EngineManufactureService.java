package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;
import java.util.Set;

public interface EngineManufactureService {
    List<String> getAllName();

    List<DataByIdResponse> getDataByIdResponse();
    Boolean update(UpdateDataRequest updateData);


    List<DataByIdResponse> getCroppedData(EngineRequest engineRequest);

    String save(SaveDataRequest saveData);

    List<EngineManufacturer> getAllEngineManufacture();

    void imprt(ImprtDataRequest imprtData);
}
