package com.vshvet.firstrelease.payload.Response;

import java.util.ArrayList;
import java.util.Map;

public class AutoEngineMapByParamResponse {
    private ArrayList<Map<String, Object>> list;
    private Integer countResult;

    public AutoEngineMapByParamResponse() {
    }

    public AutoEngineMapByParamResponse(ArrayList<Map<String, Object>> list, Integer countResult) {
        this.list = list;
        this.countResult = countResult;
    }

    public ArrayList<Map<String, Object>> getList() {
        return list;
    }

    public void setList(ArrayList<Map<String, Object>> list) {
        this.list = list;
    }

    public Integer getCountResult() {
        return countResult;
    }

    public void setCountResult(Integer countResult) {
        this.countResult = countResult;
    }
}
