package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;

public interface FuelTypeService {
    List<String> getAllName();
    List<DataByIdResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);
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
