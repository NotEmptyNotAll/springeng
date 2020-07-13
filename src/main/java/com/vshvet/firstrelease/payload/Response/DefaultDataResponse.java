package com.vshvet.firstrelease.payload.Response;

import java.util.List;
import java.util.Set;

public class DefaultDataResponse {
    private List<DataByIdResponse> autoModel;
    private List<DataByIdResponse> fuelType;
    private List<DataByIdResponse> engineManufacture;
    private List<DataByIdResponse> engineType;
    private List<DataByIdResponse> engineNumber;

    public DefaultDataResponse() {
    }

    public DefaultDataResponse(List<DataByIdResponse> autoModel,
                               List<DataByIdResponse> fuelType,
                               List<DataByIdResponse> engineManufacture,
                               List<DataByIdResponse> engineType,
                               List<DataByIdResponse> engineNumber) {
        this.autoModel = autoModel;
        this.fuelType = fuelType;
        this.engineManufacture = engineManufacture;
        this.engineType = engineType;
        this.engineNumber = engineNumber;
    }

    public List<DataByIdResponse> getAutoModel() {
        return autoModel;
    }

    public void setAutoModel(List<DataByIdResponse> autoModel) {
        this.autoModel = autoModel;
    }

    public List<DataByIdResponse> getFuelType() {
        return fuelType;
    }

    public void setFuelType(List<DataByIdResponse> fuelType) {
        this.fuelType = fuelType;
    }

    public List<DataByIdResponse> getEngineManufacture() {
        return engineManufacture;
    }

    public void setEngineManufacture(List<DataByIdResponse> engineManufacture) {
        this.engineManufacture = engineManufacture;
    }

    public List<DataByIdResponse> getEngineType() {
        return engineType;
    }

    public void setEngineType(List<DataByIdResponse> engineType) {
        this.engineType = engineType;
    }

    public List<DataByIdResponse> getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(List<DataByIdResponse> engineNumber) {
        this.engineNumber = engineNumber;
    }
}

