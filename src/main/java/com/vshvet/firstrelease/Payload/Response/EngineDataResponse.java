package com.vshvet.firstrelease.Payload.Response;

public class EngineDataResponse {

    private int id;
    private String data;
    private String engineManufacturer;
    private String cylindersPlacement;
    private String fuelType;
    private String superchargedType;
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
    private String status;

    public EngineDataResponse(int id,
                              String data,
                              String engineManufacturer,
                              String cylindersPlacement,
                              String fuelType,
                              String superchargedType,
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
                              String status) {
        this.id = id;
        this.data = data;
        this.engineManufacturer = engineManufacturer;
        this.cylindersPlacement = cylindersPlacement;
        this.fuelType = fuelType;
        this.superchargedType = superchargedType;
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
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEngineManufacturer() {
        return engineManufacturer;
    }

    public void setEngineManufacturer(String engineManufacturer) {
        this.engineManufacturer = engineManufacturer;
    }

    public String getCylindersPlacement() {
        return cylindersPlacement;
    }

    public void setCylindersPlacement(String cylindersPlacement) {
        this.cylindersPlacement = cylindersPlacement;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getSuperchargedType() {
        return superchargedType;
    }

    public void setSuperchargedType(String superchargedType) {
        this.superchargedType = superchargedType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
