package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutoModel;
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
    List<DataByIdResponse> delete(Integer id);

    FuelType findByName(String name);

    void save(FuelType fuelType);


    List<DataByIdResponse> getCroppedData(EngineRequest engineRequest);

    String save(SaveDataRequest saveData);
    Boolean update(UpdateDataRequest updateData);


    List<DataByIdResponse> getDataByIdResponse();

    void imprt(ImprtDataRequest imprtData);
}
