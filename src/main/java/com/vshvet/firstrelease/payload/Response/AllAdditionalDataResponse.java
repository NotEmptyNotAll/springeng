package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.*;

import java.util.List;

public class AllAdditionalDataResponse {
    private List<EngineDataResponse> engine;
    private List<DataByIdResponse> autoModel;
    private List<DataByIdResponse> fuelType;
    private List<DataByIdResponse> engineManufacture;
    private List<DataByIdResponse> engineNumber;
    private List<DataByIdResponse> cylinders;
    private List<DataByIdResponse> superchargeType;
    private List<DataByIdResponse> autoManufacture;
    private List<DataByIdResponse> parameterName;
    private List<DataByIdResponse> units;
    private List<AutoDataResponse> autoEng;


    public AllAdditionalDataResponse() {
    }

    public AllAdditionalDataResponse(List<DataByIdResponse> autoModel,
                                     List<DataByIdResponse> fuelType,
                                     List<DataByIdResponse> engineManufacture,
                                     List<DataByIdResponse> engineNumber,
                                     List<DataByIdResponse> cylinders,
                                     List<DataByIdResponse> superchargeType,
                                     List<DataByIdResponse> autoManufacture,
                                     List<DataByIdResponse> parameterName,
                                     List<EngineDataResponse> engine,
                                     List<DataByIdResponse> units,
                                     List<AutoDataResponse> autoEng) {
        this.units = units;
        this.autoEng = autoEng;
        this.engine = engine;
        this.autoModel = autoModel;
        this.fuelType = fuelType;
        this.engineManufacture = engineManufacture;
        this.engineNumber = engineNumber;
        this.cylinders = cylinders;
        this.superchargeType = superchargeType;
        this.autoManufacture = autoManufacture;
        this.parameterName = parameterName;
    }

    public List<AutoDataResponse> getAutoEng() {
        return autoEng;
    }

    public void setAutoEng(List<AutoDataResponse> autoEng) {
        this.autoEng = autoEng;
    }

    public List<DataByIdResponse> getUnits() {
        return units;
    }

    public void setUnits(List<DataByIdResponse> units) {
        this.units = units;
    }

    public List<EngineDataResponse> getEngine() {
        return engine;
    }

    public void setEngine(List<EngineDataResponse> engine) {
        this.engine = engine;
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

    public List<DataByIdResponse> getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(List<DataByIdResponse> engineNumber) {
        this.engineNumber = engineNumber;
    }

    public List<DataByIdResponse> getCylinders() {
        return cylinders;
    }

    public void setCylinders(List<DataByIdResponse> cylinders) {
        this.cylinders = cylinders;
    }

    public List<DataByIdResponse> getSuperchargeType() {
        return superchargeType;
    }

    public void setSuperchargeType(List<DataByIdResponse> superchargeType) {
        this.superchargeType = superchargeType;
    }

    public List<DataByIdResponse> getAutoManufacture() {
        return autoManufacture;
    }

    public void setAutoManufacture(List<DataByIdResponse> autoManufacture) {
        this.autoManufacture = autoManufacture;
    }

    public List<DataByIdResponse> getParameterName() {
        return parameterName;
    }

    public void setParameterName(List<DataByIdResponse> parameterName) {
        this.parameterName = parameterName;
    }
}
