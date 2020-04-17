package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.AutomobileEngine;

public class AutomobileEngineResponse {

    private  Integer id;
    private  Integer flapNumber;
    private  Integer cylindersNumber;
    private String autoManufacture;
    private String engineManufacture;
    private Integer powerKWT;
    private Integer engineCapacity;
    private Integer horsepower;
    private Integer pistonStoke;
    private Double pistonDiameter;
    private String modelName;
    private String engineType;
    private Integer releaseYearFrom;
    private Integer releaseYearBy;
    private Integer elemID;
    private Integer cylinderNum;
    private String cylinderPlace;


    public AutomobileEngineResponse() {
    }

    public AutomobileEngineResponse(AutomobileEngine automobileEngine) {
        this.autoManufacture = automobileEngine.getAutoManufactureByAutoManufactureFk().getManufactureName();
        this.modelName = automobileEngine.getAutoModelByAutoModelFk().getModelName();
        this.engineType = automobileEngine.getEngineByEngineFk().getEngineType();
        this.releaseYearFrom = automobileEngine.getReleaseYearFrom();
        this.releaseYearBy = automobileEngine.getReleaseYearFrom();
        this.engineManufacture=automobileEngine.getEngineByEngineFk().getEngineManufacturerByEngineManufacturerFk().getNameManufacturer();
        this.powerKWT=automobileEngine.getEngineByEngineFk().getPowerKwt();
        this.cylindersNumber=automobileEngine.getEngineByEngineFk().getCylindersNumber();
        this.flapNumber=automobileEngine.getEngineByEngineFk().getFlapNumber();
        this.engineCapacity=automobileEngine.getEngineByEngineFk().getEngineCapacity();
        this.horsepower=automobileEngine.getEngineByEngineFk().getHorsepower();
        this.pistonStoke=automobileEngine.getEngineByEngineFk().getPistonStroke();
        this.pistonDiameter=automobileEngine.getEngineByEngineFk().getPistonDiameter();
        this.id=automobileEngine.getId();
        this.elemID=automobileEngine.getElemId();
        this.cylinderNum=automobileEngine.getEngineByEngineFk().getCylindersNumber();
        this.cylinderPlace=automobileEngine.getEngineByEngineFk().getCylindersByCylindersPlacementFk().getTypeName();
    }


    public String getCylinderPlace() {
        return cylinderPlace;
    }

    public void setCylinderPlace(String cylinderPlace) {
        this.cylinderPlace = cylinderPlace;
    }

    public Integer getCylinderNum() {
        return cylinderNum;
    }

    public void setCylinderNum(Integer cylinderNum) {
        this.cylinderNum = cylinderNum;
    }

    public Integer getElemID() {
        return elemID;
    }

    public void setElemID(Integer elemID) {
        this.elemID = elemID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPistonDiameter() {
        return pistonDiameter;
    }

    public void setPistonDiameter(Double pistonDiameter) {
        this.pistonDiameter = pistonDiameter;
    }

    public Integer getPistonStoke() {
        return pistonStoke;
    }

    public void setPistonStoke(Integer pistonStoke) {
        this.pistonStoke = pistonStoke;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getFlapNumber() {
        return flapNumber;
    }

    public void setFlapNumber(Integer flapNumber) {
        this.flapNumber = flapNumber;
    }

    public Integer getCylindersNumber() {
        return cylindersNumber;
    }

    public void setCylindersNumber(Integer cylindersNumber) {
        this.cylindersNumber = cylindersNumber;
    }

    public String getAutoManufacture() {
        return autoManufacture;
    }

    public void setAutoManufacture(String autoManufacture) {
        this.autoManufacture = autoManufacture;
    }

    public String getEngineManufacture() {
        return engineManufacture;
    }

    public void setEngineManufacture(String engineManufacture) {
        this.engineManufacture = engineManufacture;
    }

    public Integer getPowerKWT() {
        return powerKWT;
    }

    public void setPowerKWT(Integer powerKWT) {
        this.powerKWT = powerKWT;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getManufactureName() {
        return autoManufacture;
    }

    public void setManufactureName(String manufactureName) {
        this.autoManufacture = manufactureName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Integer getReleaseYearFrom() {
        return releaseYearFrom;
    }

    public void setReleaseYearFrom(Integer releaseYearFrom) {
        this.releaseYearFrom = releaseYearFrom;
    }

    public Integer getReleaseYearBy() {
        return releaseYearBy;
    }

    public void setReleaseYearBy(Integer releaseYearBy) {
        this.releaseYearBy = releaseYearBy;
    }

    @Override
    public String toString() {
        return "AutomobileEngineResponse{" +
                "manufactureName='" + engineManufacture + '\'' +
                ", modelName='" + modelName + '\'' +
                ", engineType='" + engineType + '\'' +
                ", releaseYearFrom=" + releaseYearFrom +
                ", releaseYearBy=" + releaseYearBy +
                '}';
    }
}
