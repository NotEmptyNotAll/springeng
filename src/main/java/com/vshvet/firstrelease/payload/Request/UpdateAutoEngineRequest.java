package com.vshvet.firstrelease.payload.Request;

import com.vshvet.firstrelease.Entity.AutomobileEngine;

public class UpdateAutoEngineRequest {
    private int id;
    private int autoManufactureFk;
    private int autoModelFk;
    private Integer engineFk;
    private Integer releaseYearFrom;
    private Integer releaseYearBy;
    private Integer elemId;
    private boolean editRow;
    private  Integer status;


    public UpdateAutoEngineRequest(int id,
                                   int autoManufactureFk,
                                   int autoModelFk,
                                   Integer engineFk,
                                   Integer releaseYearFrom,
                                   Integer releaseYearBy,
                                   Integer elemId,
                                   boolean editRow) {
        this.elemId = elemId;
        this.id = id;
        this.autoManufactureFk = autoManufactureFk;
        this.autoModelFk = autoModelFk;
        this.engineFk = engineFk;
        this.releaseYearFrom = releaseYearFrom;
        this.releaseYearBy = releaseYearBy;
    }

    public UpdateAutoEngineRequest() {
    }

    public UpdateAutoEngineRequest(AutomobileEngine automobileEngine) {
        this.id = automobileEngine.getId();
        this.autoManufactureFk = automobileEngine.getAutoManufactureFk();
        this.autoModelFk = automobileEngine.getAutoModelFk();
        this.engineFk = automobileEngine.getEngineFk();
        this.releaseYearFrom = automobileEngine.getReleaseYearFrom();
        this.releaseYearBy = automobileEngine.getReleaseYearBy();
        this.elemId = automobileEngine.getElemId();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isEditRow() {
        return editRow;
    }

    public void setEditRow(boolean editRow) {
        this.editRow = editRow;
    }

    public Integer getElemId() {
        return elemId;
    }

    public void setElemId(Integer elemId) {
        this.elemId = elemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAutoManufactureFk() {
        return autoManufactureFk;
    }

    public void setAutoManufactureFk(int autoManufactureFk) {
        this.autoManufactureFk = autoManufactureFk;
    }

    public int getAutoModelFk() {
        return autoModelFk;
    }

    public void setAutoModelFk(int autoModelFk) {
        this.autoModelFk = autoModelFk;
    }

    public Integer getEngineFk() {
        return engineFk;
    }

    public void setEngineFk(Integer engineFk) {
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

}
