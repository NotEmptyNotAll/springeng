package com.vshvet.firstrelease.Payload.Request;

public class UpdateDataRequest {
    private Integer objToBeChanged;
    private String updateData;
    private Integer status;

    public UpdateDataRequest() {
    }

    public UpdateDataRequest(Integer id, String updateData) {
        this.objToBeChanged = id;
        this.updateData = updateData;
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

    public String getUpdateData() {
        return updateData;
    }

    public void setUpdateData(String updateData) {
        this.updateData = updateData;
    }
}
