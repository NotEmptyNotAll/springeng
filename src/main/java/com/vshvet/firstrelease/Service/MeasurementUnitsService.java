package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Response.MeasurementUnitsResponse;

import java.util.List;

public interface MeasurementUnitsService {
    List<MeasurementUnitsResponse> getAllUnits();
}
