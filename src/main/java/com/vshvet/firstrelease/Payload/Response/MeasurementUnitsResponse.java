package com.vshvet.firstrelease.Payload.Response;

import com.vshvet.firstrelease.Entity.MeasurementUnits;

public class MeasurementUnitsResponse {

    private String shortName;

    private String fullName;


    public MeasurementUnitsResponse(MeasurementUnits units) {
        this.shortName = units.getShortNameM();
        this.fullName = units.getFullNameM();

    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
