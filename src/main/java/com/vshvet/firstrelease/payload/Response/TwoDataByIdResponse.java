package com.vshvet.firstrelease.payload.Response;

public class TwoDataByIdResponse {

    private String prime_data;
    private String secondary_data;
    private Integer id;
    private String status;

    public TwoDataByIdResponse() {
    }

    public TwoDataByIdResponse(String prime_data, String secondary_data, Integer id, String status) {
        this.prime_data = prime_data;
        this.secondary_data = secondary_data;
        this.id = id;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPrime_data() {
        return prime_data;
    }

    public void setPrime_data(String prime_data) {
        this.prime_data = prime_data;
    }

    public String getSecondary_data() {
        return secondary_data;
    }

    public void setSecondary_data(String secondary_data) {
        this.secondary_data = secondary_data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
