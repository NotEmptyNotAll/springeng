package com.vshvet.firstrelease.Payload.Request;

public class ParamsRequest {
    private Integer parameterChildId;
    private Integer parameterNodeId;
    private Integer unitsFullName;
    private String parameterNumber;

    public ParamsRequest() {
    }

    public ParamsRequest(Integer parameterChildId, Integer parameterNodeId, Integer unitsFullName, String parameterNumber) {
        this.parameterChildId = parameterChildId;
        this.parameterNodeId = parameterNodeId;
        this.unitsFullName = unitsFullName;
        this.parameterNumber = parameterNumber;

    }


    public Integer getParameterChildId() {
        return parameterChildId;
    }

    public void setParameterChildId(Integer parameterChildId) {
        this.parameterChildId = parameterChildId;
    }

    public Integer getParameterNodeId() {
        return parameterNodeId;
    }

    public void setParameterNodeId(Integer parameterNodeId) {
        this.parameterNodeId = parameterNodeId;
    }

    public Integer getUnitsFullName() {
        return unitsFullName;
    }

    public void setUnitsFullName(Integer unitsFullName) {
        this.unitsFullName = unitsFullName;
    }

    public String getParameterNumber() {
        return parameterNumber;
    }

    public void setParameterNumber(String parameterNumber) {
        this.parameterNumber = parameterNumber;
    }
}
