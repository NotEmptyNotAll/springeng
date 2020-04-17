package com.vshvet.firstrelease.payload.Request;

import java.util.List;

public class EngineRequest {

    private String engineType;
    private String autoManufacturer;
    private String autoModel;
    private Integer produceYear;
    private String numberEng;
    private String fuelType;
    private Integer powerKWt;
    private Integer engineCapacity;
    private List<ParamsRequest> paramList;

    public EngineRequest() {
    }

    public EngineRequest(List<ParamsRequest> paramList,String engineType, String engineManufacturer, String autoModel, Integer produceYear, String numberEng, String fuelType, Integer powerKWt, Integer engineCapacity) {
        this.engineType = engineType;
        this.autoManufacturer = engineManufacturer;
        this.autoModel = autoModel;
        this.produceYear = produceYear;
        this.numberEng = numberEng;
        this.fuelType = fuelType;
        this.powerKWt = powerKWt;
        this.engineCapacity = engineCapacity;

        this.paramList=paramList;
    }

    public List<ParamsRequest> getParamList() {
        return paramList;
    }

    public void setParamList(List<ParamsRequest> paramList) {
        this.paramList = paramList;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getAutoManufacturer() {
        return autoManufacturer;
    }

    public void setAutoManufacturer(String autoManufacturer) {
        this.autoManufacturer = autoManufacturer;
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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getPowerKWt() {
        return powerKWt;
    }

    public void setPowerKWt(Integer powerKWt) {
        this.powerKWt = powerKWt;
    }

    public String getNumberEng() {
        return numberEng;
    }

    public void setNumberEng(String numberEng) {
        this.numberEng = numberEng;
    }
}
