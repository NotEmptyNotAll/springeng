package com.vshvet.firstrelease.Payload.Request;

import java.util.List;
public class ImprtOrUpdateEngineRequest {
    private List<SaveOrUpdateEngineRequest> list;

    public ImprtOrUpdateEngineRequest() {
    }

    public ImprtOrUpdateEngineRequest(List<SaveOrUpdateEngineRequest> list) {
        this.list = list;
    }

    public List<SaveOrUpdateEngineRequest> getList() {
        return list;
    }

    public void setList(List<SaveOrUpdateEngineRequest> list) {
        this.list = list;
    }
}
