package com.vshvet.firstrelease.Payload.Request;

public class SaveTwoDataRequest {
    private String saveData_primary;
    private String saveData_secondary;
    private Integer status;

    public SaveTwoDataRequest() {
    }

    public SaveTwoDataRequest(String saveData_primary, String saveData_secondary) {

        this.saveData_primary = saveData_primary;
        this.saveData_secondary = saveData_secondary;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSaveData_primary() {
        return saveData_primary;
    }

    public void setSaveData_primary(String saveData_primary) {
        this.saveData_primary = saveData_primary;
    }

    public String getSaveData_secondary() {
        return saveData_secondary;
    }

    public void setSaveData_secondary(String saveData_secondary) {
        this.saveData_secondary = saveData_secondary;
    }
}
