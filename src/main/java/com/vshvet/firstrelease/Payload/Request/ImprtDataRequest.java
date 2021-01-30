package com.vshvet.firstrelease.Payload.Request;

import java.util.List;

public class ImprtDataRequest {

    private List<SaveDataRequest> list;

    public ImprtDataRequest() {
    }

    public ImprtDataRequest(List<SaveDataRequest> list) {
        this.list = list;
    }

    public List<SaveDataRequest> getList() {
        return list;
    }

    public void setList(List<SaveDataRequest> list) {
        this.list = list;
    }
}
