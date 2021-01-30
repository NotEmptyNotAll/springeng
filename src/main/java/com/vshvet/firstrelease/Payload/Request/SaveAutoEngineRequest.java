package com.vshvet.firstrelease.Payload.Request;

import java.sql.Date;

public class SaveAutoEngineRequest {
    private int autoManufactureFk;
    private int autoModelFk;
    private Integer engineFk;
    private Integer releaseYearFrom;
    private Integer releaseYearBy;
    private Integer elemId;
    private Date date;
    private Integer status;



    public SaveAutoEngineRequest() {
    }

    public SaveAutoEngineRequest(int autoManufactureFk,
                                 int autoModelFk,
                                 Integer engineFk,
                                 Integer releaseYearFrom,
                                 Integer releaseYearBy,
                                 Integer elemId,
                                 Date date) {
        this.autoManufactureFk = autoManufactureFk;
        this.autoModelFk = autoModelFk;
        this.engineFk = engineFk;
        this.releaseYearFrom = releaseYearFrom;
        this.releaseYearBy = releaseYearBy;
        this.elemId = elemId;
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getElemId() {
        return elemId;
    }

    public void setElemId(Integer elemId) {
        this.elemId = elemId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
