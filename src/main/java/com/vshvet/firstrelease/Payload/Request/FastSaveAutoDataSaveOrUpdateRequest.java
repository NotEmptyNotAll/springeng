package com.vshvet.firstrelease.Payload.Request;

public class FastSaveAutoDataSaveOrUpdateRequest {

    private Integer id;
    private Integer flapNumber;
    private Integer fuelType;
    private Integer cylindersNumber;
    private Integer autoManufacture;
    private Integer engineManufacture;
    private String powerKWT;
    private Integer engineCapacity;
    private String pistonDiameterAndStoke;
    private Integer horsepower;
    private Integer pistonStoke;
    private Double pistonDiameter;
    private Integer modelName;
    private String engineType;
    private String engineId;
    private String releaseYear;
    private Integer releaseYearBy;
    private Integer releaseYearFrom;
    private Integer cylinderNum;
    private Integer cylinderPlace;
    private String superchargedType;
    private Double degreeCompression;

    public FastSaveAutoDataSaveOrUpdateRequest() {
    }

    public FastSaveAutoDataSaveOrUpdateRequest(Integer id, Integer flapNumber, Integer fuelType, Integer cylindersNumber, Integer autoManufacture, Integer engineManufacture, String powerKWT, Integer engineCapacity, String pistonDiameterAndStoke, Integer horsepower, Integer pistonStoke, Double pistonDiameter, Integer modelName, String engineType, String engineId, String releaseYear, Integer releaseYearBy, Integer releaseYearFrom, Integer cylinderNum, Integer cylinderPlace, String superchargedType, Double degreeCompression) {
        this.id = id;
        this.flapNumber = flapNumber;
        this.fuelType = fuelType;
        this.cylindersNumber = cylindersNumber;
        this.autoManufacture = autoManufacture;
        this.engineManufacture = engineManufacture;
        this.powerKWT = powerKWT;
        this.engineCapacity = engineCapacity;
        this.pistonDiameterAndStoke = pistonDiameterAndStoke;
        this.horsepower = horsepower;
        this.pistonStoke = pistonStoke;
        this.pistonDiameter = pistonDiameter;
        this.modelName = modelName;
        this.engineType = engineType;
        this.engineId = engineId;
        this.releaseYear = releaseYear;
        this.releaseYearBy = releaseYearBy;
        this.releaseYearFrom = releaseYearFrom;
        this.cylinderNum = cylinderNum;
        this.cylinderPlace = cylinderPlace;
        this.superchargedType = superchargedType;
        this.degreeCompression = degreeCompression;
    }

    public Integer getReleaseYearBy() {
        return releaseYearBy;
    }

    public void setReleaseYearBy(Integer releaseYearBy) {
        this.releaseYearBy = releaseYearBy;
    }

    public Integer getReleaseYearFrom() {
        return releaseYearFrom;
    }

    public void setReleaseYearFrom(Integer releaseYearFrom) {
        this.releaseYearFrom = releaseYearFrom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlapNumber() {
        return flapNumber;
    }

    public void setFlapNumber(Integer flapNumber) {
        this.flapNumber = flapNumber;
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getCylindersNumber() {
        return cylindersNumber;
    }

    public void setCylindersNumber(Integer cylindersNumber) {
        this.cylindersNumber = cylindersNumber;
    }

    public Integer getAutoManufacture() {
        return autoManufacture;
    }

    public void setAutoManufacture(Integer autoManufacture) {
        this.autoManufacture = autoManufacture;
    }

    public Integer getEngineManufacture() {
        return engineManufacture;
    }

    public void setEngineManufacture(Integer engineManufacture) {
        this.engineManufacture = engineManufacture;
    }

    public String getPowerKWT() {
        return powerKWT;
    }

    public void setPowerKWT(String powerKWT) {
        this.powerKWT = powerKWT;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getPistonDiameterAndStoke() {
        return pistonDiameterAndStoke;
    }

    public void setPistonDiameterAndStoke(String pistonDiameterAndStoke) {
        this.pistonDiameterAndStoke = pistonDiameterAndStoke;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getPistonStoke() {
        return pistonStoke;
    }

    public void setPistonStoke(Integer pistonStoke) {
        this.pistonStoke = pistonStoke;
    }

    public Double getPistonDiameter() {
        return pistonDiameter;
    }

    public void setPistonDiameter(Double pistonDiameter) {
        this.pistonDiameter = pistonDiameter;
    }

    public Integer getModelName() {
        return modelName;
    }

    public void setModelName(Integer modelName) {
        this.modelName = modelName;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineId() {
        return engineId;
    }

    public void setEngineId(String engineId) {
        this.engineId = engineId;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getCylinderNum() {
        return cylinderNum;
    }

    public void setCylinderNum(Integer cylinderNum) {
        this.cylinderNum = cylinderNum;
    }

    public Integer getCylinderPlace() {
        return cylinderPlace;
    }

    public void setCylinderPlace(Integer cylinderPlace) {
        this.cylinderPlace = cylinderPlace;
    }

    public String getSuperchargedType() {
        return superchargedType;
    }

    public void setSuperchargedType(String superchargedType) {
        this.superchargedType = superchargedType;
    }

    public Double getDegreeCompression() {
        return degreeCompression;
    }

    public void setDegreeCompression(Double degreeCompression) {
        this.degreeCompression = degreeCompression;
    }
}
