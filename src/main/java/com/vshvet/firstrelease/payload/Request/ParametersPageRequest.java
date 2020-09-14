package com.vshvet.firstrelease.payload.Request;

import java.util.List;
import java.util.Map;

public class ParametersPageRequest {

    private Integer id;
    private Integer flapNumber;
    private String fuelType;
    private Integer cylindersNumber;
    private String autoManufacture;
    private String engineManufacture;
    private String powerKWT;
    private Integer engineCapacity;
    private String pistonDiameterAndStoke;
    private Integer horsepower;
    private Integer pistonStoke;
    private Double pistonDiameter;
    private String modelName;
    private String engineType;
    private String releaseYear;
    private Integer cylinderNum;
    private String cylinderPlace;
    private String superchargedType;
    private Double degreeCompression;
    private List<ParamsRequest> paramList;

    private Integer pageSize;
    private Integer initRecordFrom;

    public ParametersPageRequest() {
    }

    public ParametersPageRequest(Integer pageSize, Integer initRecordFrom) {
        this.pageSize = pageSize;
        this.initRecordFrom = initRecordFrom;
    }


    public String getPistonDiameterAndStoke() {
        return pistonDiameterAndStoke;
    }

    public void setPistonDiameterAndStoke(String pistonDiameterAndStoke) {
        this.pistonDiameterAndStoke = pistonDiameterAndStoke;
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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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

    public String getCylinderPlace() {
        return cylinderPlace;
    }

    public void setCylinderPlace(String cylinderPlace) {
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

    public List<ParamsRequest> getParamList() {
        return paramList;
    }

    public void setParamList(List<ParamsRequest> paramList) {
        this.paramList = paramList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getInitRecordFrom() {
        return initRecordFrom;
    }

    public void setInitRecordFrom(Integer initRecordFrom) {
        this.initRecordFrom = initRecordFrom;
    }
}
