package com.vshvet.firstrelease.payload.Request;

public class ParamsRequest {
    private String parameterName;
    private String unitsFullName;
    private Double parameterNumber;

    public ParamsRequest() {
    }

    public ParamsRequest(String parameterName, String unitsFullName, Double parameterNumber) {
        this.parameterName = parameterName;
        this.unitsFullName = unitsFullName;
        this.parameterNumber = parameterNumber;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getUnitsFullName() {
        return unitsFullName;
    }

    public void setUnitsFullName(String unitsFullName) {
        this.unitsFullName = unitsFullName;
    }

    public Double getParameterNumber() {
        return parameterNumber;
    }

    public void setParameterNumber(Double parameterNumber) {
        this.parameterNumber = parameterNumber;
    }
}
