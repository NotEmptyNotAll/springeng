package com.vshvet.firstrelease.payload.Request;

import java.util.List;

public class FastAutoEngineSaveOrUpdateRequest {

    private Integer id;
    private Integer flapNumber;
    private String fuelType;
    private Integer cylindersNumber;
    private String autoManufacture;
    private String engineManufacture;
    private String powerKWT;
    private Integer engineCapacity;
    private Integer horsepower;
    private Double pistonStoke;
    private Double pistonDiameter;
    private String modelName;
    private String engineType;
    private String releaseYear;
    private Integer engineTypeId;
    private Integer modelNameId;
    private Integer engManufactureId;
    private Integer cylinderNum;
    private String cylinderPlace;
    private String superchargedType;
    private Double degreeCompression;
    private boolean paramAction;
    private boolean autoEngSaveAction;
    private List<SaveOrUpdateParametersRequest> paramList;


    public FastAutoEngineSaveOrUpdateRequest(Integer id, Integer flapNumber, String fuelType, Integer cylindersNumber, String autoManufacture, String engineManufacture, String powerKWT, Integer engineCapacity, Integer horsepower, Double pistonStoke, Double pistonDiameter, String modelName, String engineType, String releaseYear, Integer engineTypeId, Integer modelNameId, Integer engManufactureId, Integer cylinderNum, String cylinderPlace, String superchargedType, Double degreeCompression, boolean paramAction, boolean autoEngSaveAction, List<SaveOrUpdateParametersRequest> paramList) {
        this.id = id;
        this.flapNumber = flapNumber;
        this.fuelType = fuelType;
        this.cylindersNumber = cylindersNumber;
        this.autoManufacture = autoManufacture;
        this.engineManufacture = engineManufacture;
        this.powerKWT = powerKWT;
        this.engineCapacity = engineCapacity;
        this.horsepower = horsepower;
        this.pistonStoke = pistonStoke;
        this.pistonDiameter = pistonDiameter;
        this.modelName = modelName;
        this.engineType = engineType;
        this.releaseYear = releaseYear;
        this.engineTypeId = engineTypeId;
        this.modelNameId = modelNameId;
        this.engManufactureId = engManufactureId;
        this.cylinderNum = cylinderNum;
        this.cylinderPlace = cylinderPlace;
        this.superchargedType = superchargedType;
        this.degreeCompression = degreeCompression;
        this.paramAction = paramAction;
        this.autoEngSaveAction = autoEngSaveAction;
        this.paramList = paramList;
    }

    public Integer getModelNameId() {
        return modelNameId;
    }

    public void setModelNameId(Integer modelNameId) {
        this.modelNameId = modelNameId;
    }

    public Integer getEngManufactureId() {
        return engManufactureId;
    }

    public void setEngManufactureId(Integer engManufactureId) {
        this.engManufactureId = engManufactureId;
    }

    public List<SaveOrUpdateParametersRequest> getParamList() {
        return paramList;
    }

    public void setParamList(List<SaveOrUpdateParametersRequest> paramList) {
        this.paramList = paramList;
    }

    public Integer getEngineTypeId() {
        return engineTypeId;
    }

    public void setEngineTypeId(Integer engineTypeId) {
        this.engineTypeId = engineTypeId;
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

    public Double getPistonStoke() {
        return pistonStoke;
    }

    public void setPistonStoke(Double pistonStoke) {
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

    public boolean isParamAction() {
        return paramAction;
    }

    public void setParamAction(boolean paramAction) {
        this.paramAction = paramAction;
    }

    public boolean isAutoEngSaveAction() {
        return autoEngSaveAction;
    }

    public void setAutoEngSaveAction(boolean autoEngSaveAction) {
        this.autoEngSaveAction = autoEngSaveAction;
    }

}
