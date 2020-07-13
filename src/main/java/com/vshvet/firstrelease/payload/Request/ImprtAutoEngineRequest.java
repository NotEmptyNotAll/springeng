package com.vshvet.firstrelease.payload.Request;

import java.util.List;

public class ImprtAutoEngineRequest {
    private List<SaveAutoEngineRequest> list;

    public ImprtAutoEngineRequest() {
    }

    public ImprtAutoEngineRequest(List<SaveAutoEngineRequest> list) {
        this.list = list;
    }

    public List<SaveAutoEngineRequest> getList() {
        return list;
    }

    public void setList(List<SaveAutoEngineRequest> list) {
        this.list = list;
    }
}
