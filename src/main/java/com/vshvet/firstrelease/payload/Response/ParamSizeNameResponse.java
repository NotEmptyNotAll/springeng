package com.vshvet.firstrelease.payload.Response;

public class ParamSizeNameResponse {
    private String name;

    private String short_name;

    private boolean isEmpty;
    private Integer sortNumber;

    public ParamSizeNameResponse() {
    }

    public ParamSizeNameResponse(String name,
                                 String short_name,
                                 Integer sortNumber) {
        this.sortNumber=sortNumber;
        this.name = name;
        this.short_name = short_name;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
