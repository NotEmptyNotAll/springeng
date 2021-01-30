package com.vshvet.firstrelease.Payload.Request;

import java.util.List;

public class ImprtTwoDataRequest {
    private List<SaveTwoDataRequest> list;

    public ImprtTwoDataRequest() {
    }

    public ImprtTwoDataRequest(List<SaveTwoDataRequest> list) {
        this.list = list;
    }

    public List<SaveTwoDataRequest> getList() {
        return list;
    }

    public void setList(List<SaveTwoDataRequest> list) {
        this.list = list;
    }
}
