package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.MeasurementUnits;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.MeasurementUnitsResponse;

import java.util.List;

public interface MeasurementUnitsService {

    List<DataByIdResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);
    List<DataByIdResponse> getAllUnits();
    List<DataByIdResponse> delete(Integer id);

    Boolean update(UpdateTwoDataRequest updateData);

    String save(SaveTwoDataRequest saveData);

    void imprt(ImprtTwoDataRequest imprtData);
}
