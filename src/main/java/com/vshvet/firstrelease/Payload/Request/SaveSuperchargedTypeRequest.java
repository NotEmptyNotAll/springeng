package com.vshvet.firstrelease.Payload.Request;

public class SaveSuperchargedTypeRequest {
    private String nameType;
    private String mark;

    public SaveSuperchargedTypeRequest(String nameType, String mark) {
        this.nameType = nameType;
        this.mark = mark;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
