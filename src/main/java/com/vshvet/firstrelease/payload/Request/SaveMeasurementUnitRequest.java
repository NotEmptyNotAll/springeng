package com.vshvet.firstrelease.payload.Request;

public class SaveMeasurementUnitRequest {
    private String shortNameM;
    private String fullNameM;
    private String shortNameA;
    private Integer fullNameA;

    public SaveMeasurementUnitRequest() {
    }

    public SaveMeasurementUnitRequest(String shortNameM,
                                      String fullNameM,
                                      String shortNameA,
                                      Integer fullNameA) {
        this.shortNameM = shortNameM;
        this.fullNameM = fullNameM;
        this.shortNameA = shortNameA;
        this.fullNameA = fullNameA;
    }

    public String getShortNameM() {
        return shortNameM;
    }

    public void setShortNameM(String shortNameM) {
        this.shortNameM = shortNameM;
    }

    public String getFullNameM() {
        return fullNameM;
    }

    public void setFullNameM(String fullNameM) {
        this.fullNameM = fullNameM;
    }

    public String getShortNameA() {
        return shortNameA;
    }

    public void setShortNameA(String shortNameA) {
        this.shortNameA = shortNameA;
    }

    public Integer getFullNameA() {
        return fullNameA;
    }

    public void setFullNameA(Integer fullNameA) {
        this.fullNameA = fullNameA;
    }
}
