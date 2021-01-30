package com.vshvet.firstrelease.Service;


import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;


public interface AutoManufactureService {

    AutoManufacture findByName(String name);

    List<DataByIdResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);

    void save(AutoManufacture autoManufacture);

    List<DataByIdResponse> delete(Integer id);

    AutoManufacture findById(Integer id);

    List<String> getAllManufacture();

    Boolean update(UpdateDataRequest updateData);

    List<DataByIdResponse> getDataByIdResponse();

    List<AutoManufacture> getAllAutoManufacture();

    String save(SaveDataRequest saveData);

    void imprt(ImprtDataRequest imprtData);

    List<DataByIdResponse> getCroppedData(EngineRequest engine);
}
