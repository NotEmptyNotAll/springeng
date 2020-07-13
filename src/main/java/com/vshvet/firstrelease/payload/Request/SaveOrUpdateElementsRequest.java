package com.vshvet.firstrelease.payload.Request;

public class   SaveOrUpdateElementsRequest implements Comparable<SaveOrUpdateElementsRequest> {

    private int elemId;
    private Integer paramNameFk;
    private Integer parentId;

    public SaveOrUpdateElementsRequest() {
    }

    public SaveOrUpdateElementsRequest(int elemId, Integer paramNameFk, Integer parentId) {
        this.elemId = elemId;
        this.paramNameFk = paramNameFk;
        this.parentId = parentId;
    }

    public SaveOrUpdateElementsRequest(int elemId, Integer paramNameFk) {
        this.elemId = elemId;
        this.paramNameFk = paramNameFk;
    }

    public int getElemId() {
        return elemId;
    }

    public void setElemId(int elemId) {
        this.elemId = elemId;
    }

    public Integer getParamNameFk() {
        return paramNameFk;
    }

    public void setParamNameFk(Integer paramNameFk) {
        this.paramNameFk = paramNameFk;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public int compareTo(SaveOrUpdateElementsRequest o) {
        return parentId.compareTo(o.parentId);
    }
}
