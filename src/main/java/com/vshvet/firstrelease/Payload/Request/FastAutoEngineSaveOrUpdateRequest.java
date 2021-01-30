package com.vshvet.firstrelease.Payload.Request;

import java.util.List;

public class FastAutoEngineSaveOrUpdateRequest {

    private Integer id;
    private Integer flapNumber;
    private Integer fuelType;
    private Integer cylindersNumber;
    private Integer autoManufacture;
    private String autoManufactureName;
    private Integer engineManufacture;
    private String engineManufactureName;
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
    private Integer cylinderPlace;
    private String cylinderPlaceName;
    private Integer superchargedType;
    private String superchargedTypeId;
    private Double degreeCompression;
    private Integer releaseYearBy;
    private Integer releaseYearFrom;
    private boolean paramAction;
    private boolean autoEngSaveAction;
    private List<SaveOrUpdateParametersRequest> paramList;


    public FastAutoEngineSaveOrUpdateRequest(Integer id, Integer flapNumber, Integer fuelType, Integer cylindersNumber, Integer autoManufacture, String autoManufactureName, Integer engineManufacture, String engineManufactureName, String powerKWT, Integer engineCapacity, Integer horsepower, Double pistonStoke, Double pistonDiameter, String modelName, String engineType, String releaseYear, Integer engineTypeId, Integer modelNameId, Integer engManufactureId, Integer cylinderNum, Integer cylinderPlace, String cylinderPlaceName, Integer superchargedType, String superchargedTypeId, Double degreeCompression, Integer releaseYearBy, Integer releaseYearFrom, boolean paramAction, boolean autoEngSaveAction, List<SaveOrUpdateParametersRequest> paramList) {
        this.id = id;
        this.flapNumber = flapNumber;
        this.fuelType = fuelType;
        this.cylindersNumber = cylindersNumber;
        this.autoManufacture = autoManufacture;
        this.autoManufactureName = autoManufactureName;
        this.engineManufacture = engineManufacture;
        this.engineManufactureName = engineManufactureName;
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
        this.cylinderPlaceName = cylinderPlaceName;
        this.superchargedType = superchargedType;
        this.superchargedTypeId = superchargedTypeId;
        this.degreeCompression = degreeCompression;
        this.releaseYearBy = releaseYearBy;
        this.releaseYearFrom = releaseYearFrom;
        this.paramAction = paramAction;
        this.autoEngSaveAction = autoEngSaveAction;
        this.paramList = paramList;
    }

    public String getSuperchargedTypeId() {
        return superchargedTypeId;
    }

    public void setSuperchargedTypeId(String superchargedTypeId) {
        this.superchargedTypeId = superchargedTypeId;
    }

    public String getCylinderPlaceName() {
        return cylinderPlaceName;
    }

    public void setCylinderPlaceName(String cylinderPlaceName) {
        this.cylinderPlaceName = cylinderPlaceName;
    }

    public String getAutoManufactureName() {
        return autoManufactureName;
    }

    public void setAutoManufactureName(String autoManufactureName) {
        this.autoManufactureName = autoManufactureName;
    }

    public String getEngineManufactureName() {
        return engineManufactureName;
    }

    public void setEngineManufactureName(String engineManufactureName) {
        this.engineManufactureName = engineManufactureName;
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

    public Integer getSuperchargedType() {
        return superchargedType;
    }

    public void setSuperchargedType(Integer superchargedType) {
        this.superchargedType = superchargedType;
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

    public Integer getFlapNumber() {
        return flapNumber;
    }

    public void setFlapNumber(Integer flapNumber) {
        this.flapNumber = flapNumber;
    }


    public Integer getEngineManufacture() {
        return engineManufacture;
    }

    public void setEngineManufacture(Integer engineManufacture) {
        this.engineManufacture = engineManufacture;
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

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
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


    public Double getDegreeCompression() {
        return degreeCompression;
    }

    public void setDegreeCompression(Double degreeCompression) {
        this.degreeCompression = degreeCompression;
    }

    public boolean isParamAction() {
        return paramAction;
    }


    public Integer getCylinderPlace() {
        return cylinderPlace;
    }

    public void setCylinderPlace(Integer cylinderPlace) {
        this.cylinderPlace = cylinderPlace;
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
