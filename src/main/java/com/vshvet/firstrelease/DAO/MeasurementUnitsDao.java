package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.MeasurementUnits;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;

import java.util.List;

public interface MeasurementUnitsDao extends Dao<MeasurementUnits> {
    List<MeasurementUnits> getPagination(PaginationDataRequest request);

    Long getCountResults(PaginationDataRequest request);
}
