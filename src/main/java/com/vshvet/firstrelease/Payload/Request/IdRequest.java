package com.vshvet.firstrelease.Payload.Request;

public class IdRequest {
    private Integer id;

    private Integer auto_id;


    public IdRequest() {
    }

    public IdRequest(Integer id) {
        this.id = id;
    }

    public IdRequest(Integer id, Integer auto_id) {
        this.id = id;
        this.auto_id = auto_id;
    }

    public Integer getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(Integer auto_id) {
        this.auto_id = auto_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
