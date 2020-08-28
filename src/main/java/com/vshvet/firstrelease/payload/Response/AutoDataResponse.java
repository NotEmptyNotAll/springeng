package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.AutomobileEngine;

public class AutoDataResponse {

    private int id;
    private String autoManufactureFk;
    private String autoModelFk;
    private String engineFk;
    private Integer releaseYearFrom;
    private Integer releaseYearBy;
    private String status;

    public AutoDataResponse(AutomobileEngine automobileEngine){
        this.id = automobileEngine.getId();
        this.autoManufactureFk = automobileEngine.getAutoManufactureByAutoManufactureFk().getManufactureName();
        this.autoModelFk = automobileEngine.getAutoModelByAutoModelFk().getModelName();
        this.engineFk =automobileEngine.getEngineByEngineFk().getEngineType();
        this.releaseYearFrom = automobileEngine.getReleaseYearFrom();
        this.releaseYearBy = automobileEngine.getReleaseYearBy();
        this.status = automobileEngine.getStatus().getStatus();
    }

    public AutoDataResponse(int id,
                              String autoManufactureFk,
                              String autoModelFk,
                              String engineFk,
                              Integer releaseYearFrom,
                              Integer releaseYearBy, String status) {
        this.id = id;
        this.autoManufactureFk = autoManufactureFk;
        this.autoModelFk = autoModelFk;
        this.engineFk = engineFk;
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

    public String getAutoManufactureFk() {
        return autoManufactureFk;
    }

    public void setAutoManufactureFk(String autoManufactureFk) {
        this.autoManufactureFk = autoManufactureFk;
    }

    public String getAutoModelFk() {
        return autoModelFk;
    }

    public void setAutoModelFk(String autoModelFk) {
        this.autoModelFk = autoModelFk;
    }

    public String getEngineFk() {
        return engineFk;
    }

    public void setEngineFk(String engineFk) {
        this.engineFk = engineFk;
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
