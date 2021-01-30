package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

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
