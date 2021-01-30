package com.vshvet.firstrelease.Payload.Request;

public class SaveDataRequest {
    private String saveData;
    private Integer status;


    public SaveDataRequest() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SaveDataRequest(String saveData) {
        this.saveData = saveData;
    }

    public String getSaveData() {
        return saveData;
    }

    public void setSaveData(String saveData) {
        this.saveData = saveData;
    }
}
