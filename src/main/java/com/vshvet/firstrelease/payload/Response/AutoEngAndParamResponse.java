package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.AutomobileEngine;

import java.util.HashMap;
import java.util.Map;

public class AutoEngAndParamResponse {

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
    private Integer elemID;
    private Integer cylinderNum;
    private String cylinderPlace;
    private String superchargedType;
    private Double degreeCompression;
    private Map<String, Object> paramMap;

    public AutoEngAndParamResponse() {
    }

    public AutoEngAndParamResponse(AutomobileEngine automobileEngine, Map<String, Object> paramMap) {
        this.paramMap = paramMap;
        this.autoManufacture = automobileEngine.getAutoManufactureByAutoManufactureFk().getManufactureName();
        this.modelName = automobileEngine.getAutoModelByAutoModelFk().getModelName();
        this.engineType = automobileEngine.getEngineByEngineFk().getEngineType();
        if (automobileEngine.getReleaseYearFrom() != null && automobileEngine.getReleaseYearBy() != null) {
            this.releaseYear = automobileEngine.getReleaseYearFrom() + " - " + automobileEngine.getReleaseYearBy().toString();

        } else if (automobileEngine.getReleaseYearFrom() == null && automobileEngine.getReleaseYearBy() != null) {
            this.releaseYear = automobileEngine.getReleaseYearBy().toString();

        } else if (automobileEngine.getReleaseYearBy() == null && automobileEngine.getReleaseYearFrom() != null) {
            this.releaseYear = automobileEngine.getReleaseYearFrom().toString();
        } else if (automobileEngine.getYears() != null) {
            this.releaseYear = automobileEngine.getYears();
        } else {
            this.releaseYear = "";
        }
        this.engineManufacture = automobileEngine.getEngineByEngineFk().getEngineManufacturerByEngineManufacturerFk().getNameManufacturer();
        this.powerKWT = automobileEngine.getEngineByEngineFk().getPowerKwt();
        this.cylindersNumber = automobileEngine.getEngineByEngineFk().getCylindersNumber();
        this.flapNumber = automobileEngine.getEngineByEngineFk().getFlapNumber();
        if(automobileEngine.getEngineByEngineFk().getEngineCapacity()!=0){
            this.engineCapacity = automobileEngine.getEngineByEngineFk().getEngineCapacity();
        }
        this.horsepower = automobileEngine.getEngineByEngineFk().getHorsepower();
        this.pistonStoke = automobileEngine.getEngineByEngineFk().getPistonStroke();
        this.pistonDiameter = automobileEngine.getEngineByEngineFk().getPistonDiameter();
        this.id = automobileEngine.getId();
        this.elemID = automobileEngine.getElemId();
        this.cylinderNum = automobileEngine.getEngineByEngineFk().getCylindersNumber();
        this.cylinderPlace = automobileEngine.getEngineByEngineFk().getCylindersByCylindersPlacementFk().getTypeName();
        this.fuelType = automobileEngine.getEngineByEngineFk().getFuelTypeByFuelTypeFk().getNameType();
        this.superchargedType = automobileEngine.getEngineByEngineFk().getSuperchargedTypeBySuperchargedTypeFk().getNameType();
        this.degreeCompression = automobileEngine.getEngineByEngineFk().getDegreeCompression();

    }

    public Map<String, Object> getMapThisElem() {
        return new HashMap<String, Object>() {{
            putAll(paramMap);
            put("autoManufacture", autoManufacture);
            put("modelName", modelName);
            put("engineType", engineType);
            put("releaseYear", releaseYear);
            put("engineManufacture", engineManufacture);
            put("powerKWT", powerKWT);
            put("cylindersNumber", cylindersNumber);
            put("flapNumber", flapNumber);
            put("engineCapacity", engineCapacity);
            put("pistonDiameterAndStoke", (pistonDiameter == null && pistonStoke == null) ? ""
                    : (pistonDiameter != null ? pistonDiameter : "") + "x" + (pistonStoke != null ? pistonStoke : ""));
            put("horsepower", horsepower);
            put("id", id);
            put("cylinderNum", cylinderNum);
            put("cylinderPlace", cylinderPlace);
            put("fuelType", fuelType);
            put("superchargedType", superchargedType);
            put("degreeCompression", degreeCompression);
        }};
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }


    public Double getDegreeCompression() {
        return degreeCompression;
    }

    public void setDegreeCompression(Double degreeCompression) {
        this.degreeCompression = degreeCompression;
    }

    public String getSuperchargedType() {
        return superchargedType;
    }

    public void setSuperchargedType(String superchargedType) {
        this.superchargedType = superchargedType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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

    public Double getPistonStoke() {
        return pistonStoke;
    }

    public void setPistonStoke(Double pistonStoke) {
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "AutoEngAndParamResponse{" +
                "id=" + id +
                ", flapNumber=" + flapNumber +
                ", fuelType='" + fuelType + '\'' +
                ", cylindersNumber=" + cylindersNumber +
                ", autoManufacture='" + autoManufacture + '\'' +
                ", engineManufacture='" + engineManufacture + '\'' +
                ", powerKWT='" + powerKWT + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", horsepower=" + horsepower +
                ", pistonStoke=" + pistonStoke +
                ", pistonDiameter=" + pistonDiameter +
                ", modelName='" + modelName + '\'' +
                ", engineType='" + engineType + '\'' +
                ", releaseYear=" + releaseYear +
                ", elemID=" + elemID +
                ", cylinderNum=" + cylinderNum +
                ", cylinderPlace='" + cylinderPlace + '\'' +
                ", superchargedType='" + superchargedType + '\'' +
                ", degreeCompression=" + degreeCompression +
                ", paramMap=" + paramMap +
                '}';
    }
}
