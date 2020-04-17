package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.AutomobileEngine;

public class EngineResponse {
    private String engineType;
    private String fuelType;
    private int engineCapacity;
    private Integer powerKwt;
    private String autoManufacture;
    private String autoModel;
    private Integer produceYear;

    public EngineResponse(AutomobileEngine engine) {
        if (engine != null) {
            this.engineType = engine.getEngineByEngineFk().getEngineType();
            this.fuelType = engine.getEngineByEngineFk().getFuelTypeByFuelTypeFk().getNameType();
            this.engineCapacity = engine.getEngineByEngineFk().getEngineCapacity();
            this.powerKwt = engine.getEngineByEngineFk().getPowerKwt();
            this.autoManufacture=engine.getAutoManufactureByAutoManufactureFk().getManufactureName();
            this.autoModel =engine.getAutoModelByAutoModelFk().getModelName();
            this.produceYear=engine.getReleaseYearFrom();
        }
    }

    public String getAutoManufacture() {
        return autoManufacture;
    }

    public void setAutoManufacture(String autoManufacture) {
        this.autoManufacture = autoManufacture;
    }

    public String getAutoModel() {
        return autoModel;
    }

    public void setAutoModel(String autoModel) {
        this.autoModel = autoModel;
    }

    public Integer getProduceYear() {
        return produceYear;
    }

    public void setProduceYear(Integer produceYear) {
        this.produceYear = produceYear;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getPowerKwt() {
        return powerKwt;
    }

    public void setPowerKwt(Integer powerKwt) {
        this.powerKwt = powerKwt;
    }
}
