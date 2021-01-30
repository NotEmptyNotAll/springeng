package com.vshvet.firstrelease.Payload.Request;

import java.sql.Date;

public class SaveOrUpdateEngineRequest {
    private Integer objToBeChanged;
    private String engineType;
    private Integer engineManufacturerFk;
    private int cylindersPlacementFk;
    private int fuelTypeFk;
    private int superchargedTypeFk;
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
    private Date date;
    private Integer status;


    public SaveOrUpdateEngineRequest() {
    }

    public SaveOrUpdateEngineRequest(String engineType,
                                     Integer engineManufacturerFk,
                                     int cylindersPlacementFk,
                                     int fuelTypeFk,
                                     int superchargedTypeFk,
                                     Integer cylindersNumber,
                                     Integer flapNumber,
                                     Double pistonDiameter,
                                     Double pistonStroke,
                                     int engineCapacity,
                                     String powerKwt,
                                     Integer horsepower,
                                     Double degreeCompression,
                                     Integer releaseYearFrom,
                                     Integer releaseYearBy,
                                     Date date,
                                     Integer objToBeChanged) {
        this.engineType = engineType;
        this.engineManufacturerFk = engineManufacturerFk;
        this.cylindersPlacementFk = cylindersPlacementFk;
        this.fuelTypeFk = fuelTypeFk;
        this.superchargedTypeFk = superchargedTypeFk;
        this.cylindersNumber = cylindersNumber;
        this.flapNumber = flapNumber;
        this.pistonDiameter = pistonDiameter;
        this.pistonStroke = pistonStroke;
        this.engineCapacity = engineCapacity;
        this.powerKwt = powerKwt;
        this.horsepower = horsepower;
        this.degreeCompression = degreeCompression;
        this.releaseYearFrom = releaseYearFrom;
        this.releaseYearBy = releaseYearBy;
        this.date = date;
        this.objToBeChanged = objToBeChanged;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getObjToBeChanged() {
        return objToBeChanged;
    }

    public void setObjToBeChanged(Integer objToBeChanged) {
        this.objToBeChanged = objToBeChanged;
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

    public int getCylindersPlacementFk() {
        return cylindersPlacementFk;
    }

    public void setCylindersPlacementFk(int cylindersPlacementFk) {
        this.cylindersPlacementFk = cylindersPlacementFk;
    }

    public int getFuelTypeFk() {
        return fuelTypeFk;
    }

    public void setFuelTypeFk(int fuelTypeFk) {
        this.fuelTypeFk = fuelTypeFk;
    }

    public int getSuperchargedTypeFk() {
        return superchargedTypeFk;
    }

    public void setSuperchargedTypeFk(int superchargedTypeFk) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
