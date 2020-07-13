package com.vshvet.firstrelease.payload.Request;

import java.util.List;

public class EngineRequest {

    private Integer engineType;
    private Integer autoManufacturer;
    private Integer autoModel;
    private Integer produceYear;
    private Integer numberEng;
    private Integer fuelType;
    private String powerKWt;
    private Integer engineCapacity;
    private List<ParamsRequest> paramList;

    public EngineRequest() {
    }

    public EngineRequest(Integer engineType,
                         Integer autoManufacturer,
                         Integer autoModel,
                         Integer produceYear,
                         Integer numberEng,
                         Integer fuelType,
                         String powerKWt,
                         Integer engineCapacity,
                         List<ParamsRequest> paramList) {
        this.engineType = engineType;
        this.autoManufacturer = autoManufacturer;
        this.autoModel = autoModel;
        this.produceYear = produceYear;
        this.numberEng = numberEng;
        this.fuelType = fuelType;
        this.powerKWt = powerKWt;
        this.engineCapacity = engineCapacity;
        this.paramList = paramList;
    }

    public Boolean findOnlyByParam() {
        if (this.engineType != null)
            return false;
        if (this.autoManufacturer != null)
            return false;
        if (this.autoModel != null)
            return false;
        if (this.fuelType != null)
            return false;
        if (this.engineCapacity != null)
            return false;
        if (this.numberEng != null)
            return false;
        if (this.powerKWt != null)
            return false;
        if (this.produceYear != null)
            return false;
        return true;
    }

    public Integer getEngineType() {
        return engineType;
    }

    public void setEngineType(Integer engineType) {
        this.engineType = engineType;
    }

    public Integer getAutoManufacturer() {
        return autoManufacturer;
    }

    public void setAutoManufacturer(Integer autoManufacturer) {
        this.autoManufacturer = autoManufacturer;
    }

    public Integer getAutoModel() {
        return autoModel;
    }

    public void setAutoModel(Integer autoModel) {
        this.autoModel = autoModel;
    }

    public Integer getProduceYear() {
        return produceYear;
    }

    public void setProduceYear(Integer produceYear) {
        this.produceYear = produceYear;
    }

    public Integer getNumberEng() {
        return numberEng;
    }

    public void setNumberEng(Integer numberEng) {
        this.numberEng = numberEng;
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
    }

    public String getPowerKWt() {
        return powerKWt;
    }

    public void setPowerKWt(String powerKWt) {
        this.powerKWt = powerKWt;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public List<ParamsRequest> getParamList() {
        return paramList;
    }

    public void setParamList(List<ParamsRequest> paramList) {
        this.paramList = paramList;
    }
}
