package com.vshvet.firstrelease.Payload.Response;

public class DataByIdResponse {
    private String data;
    private Integer id;
    private String status;
    private String secondary_data;
    public DataByIdResponse() {
    }

    public DataByIdResponse(String data, Integer id) {
        this.data = data;
        this.id = id;
    }

    public DataByIdResponse(String data, Integer id, String status) {
        this.data = data;
        this.id = id;
        this.status = status;
    }

    public DataByIdResponse(String data,String secondary_data, Integer id, String status ) {
        this.data = data;
        this.secondary_data = secondary_data;
        this.id = id;
        this.status = status;
    }

    public String getSecondary_data() {
        return secondary_data;
    }

    public void setSecondary_data(String secondary_data) {
        this.secondary_data = secondary_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
