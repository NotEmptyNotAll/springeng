package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.MeasurementUnitsResponse;

import java.util.List;

public interface MeasurementUnitsService {
    List<DataByIdResponse> getAllUnits();

    Boolean update(UpdateTwoDataRequest updateData);

    String save(SaveTwoDataRequest saveData);

    void imprt(ImprtTwoDataRequest imprtData);
}
