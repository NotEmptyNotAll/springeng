package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;
import java.util.Set;

public interface FuelTypeService {
    List<String> getAllName();

    List<FuelType> getAllFuelType();

    List<DataByIdResponse> getCroppedData(EngineRequest engineRequest);

    String save(SaveDataRequest saveData);
    Boolean update(UpdateDataRequest updateData);


    List<DataByIdResponse> getDataByIdResponse();

    void imprt(ImprtDataRequest imprtData);
}
