package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Engine;

public class FullEngineResponse {
    private Integer objToBeChanged;
    private int id;
    private String engineType;
    private Integer engineManufacturerFk;
    private Integer cylindersPlacementFk;
    private Integer fuelTypeFk;
    private Integer superchargedTypeFk;
    private Integer cylindersNumber;
    private Integer flapNumber;
    private Double pistonDiameter;
    private Double pistonStroke;
    private int engineCapacity;
    private String powerKwt;
    private Integer horsepower;
    private Double degreeCompression;
    private Integer releaseYearFrom;
        private Integer releaseYearBy;

    public FullEngineResponse(Engine engine) {
        this.id = engine.getId();
        this.engineType = engine.getEngineType();
        this.cylindersPlacementFk = engine.getCylindersPlacementFk();
        this.fuelTypeFk = engine.getFuelTypeFk();
        this.engineManufacturerFk = engine.getEngineManufacturerFk();
        this.superchargedTypeFk = engine.getSuperchargedTypeFk();
        this.cylindersNumber = engine.getCylindersNumber();
        this.flapNumber = engine.getFlapNumber();
        this.pistonDiameter = engine.getPistonDiameter();
        this.pistonStroke = engine.getPistonStroke();
        this.engineCapacity = engine.getEngineCapacity();
        this.powerKwt = engine.getPowerKwt();
        this.horsepower = engine.getHorsepower();
        this.degreeCompression = engine.getDegreeCompression();
        this.releaseYearFrom = engine.getReleaseYearFrom();
        this.releaseYearBy = engine.getReleaseYearBy();
    }

    public Integer getObjToBeChanged() {
        return objToBeChanged;
    }

    public void setObjToBeChanged(Integer objToBeChanged) {
        this.objToBeChanged = objToBeChanged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Integer getEngineManufacturerFk() {
        return engineManufacturerFk;
    }

    public void setEngineManufacturerFk(Integer engineManufacturerFk) {
        this.engineManufacturerFk = engineManufacturerFk;
    }

    public Integer getCylindersPlacementFk() {
        return cylindersPlacementFk;
    }

    public void setCylindersPlacementFk(Integer cylindersPlacementFk) {
        this.cylindersPlacementFk = cylindersPlacementFk;
    }

    public Integer getFuelTypeFk() {
        return fuelTypeFk;
    }

    public void setFuelTypeFk(Integer fuelTypeFk) {
        this.fuelTypeFk = fuelTypeFk;
    }

    public Integer getSuperchargedTypeFk() {
        return superchargedTypeFk;
    }

    public void setSuperchargedTypeFk(Integer superchargedTypeFk) {
        this.superchargedTypeFk = superchargedTypeFk;
    }

    public Integer getCylindersNumber() {
        return cylindersNumber;
    }

    public void setCylindersNumber(Integer cylindersNumber) {
        this.cylindersNumber = cylindersNumber;
    }

    public Integer getFlapNumber() {
        return flapNumber;
    }

    public void setFlapNumber(Integer flapNumber) {
        this.flapNumber = flapNumber;
    }

    public Double getPistonDiameter() {
        return pistonDiameter;
    }

    public void setPistonDiameter(Double pistonDiameter) {
        this.pistonDiameter = pistonDiameter;
    }

    public Double getPistonStroke() {
        return pistonStroke;
    }

    public void setPistonStroke(Double pistonStroke) {
        this.pistonStroke = pistonStroke;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getPowerKwt() {
        return powerKwt;
    }

    public void setPowerKwt(String powerKwt) {
        this.powerKwt = powerKwt;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Double getDegreeCompression() {
        return degreeCompression;
    }

    public void setDegreeCompression(Double degreeCompression) {
        this.degreeCompression = degreeCompression;
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
}
