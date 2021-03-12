package com.vshvet.firstrelease.Payload.Request;

import java.util.List;

public class FastParamSaveRequest {
    private List<SaveOrUpdateParametersRequest>  saveList;
    private Integer userId;

    public FastParamSaveRequest() {
    }

    public FastParamSaveRequest(List<SaveOrUpdateParametersRequest> saveList, Integer langId, Integer userId) {
        this.saveList = saveList;
        this.userId = userId;
    }

    public List<SaveOrUpdateParametersRequest> getSaveList() {
        return saveList;
    }

    public void setSaveList(List<SaveOrUpdateParametersRequest> saveList) {
        this.saveList = saveList;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
