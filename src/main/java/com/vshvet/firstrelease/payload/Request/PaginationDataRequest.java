package com.vshvet.firstrelease.payload.Request;

import java.util.HashMap;
import java.util.Map;

public class PaginationDataRequest {
    private Integer pageSize;
    private Integer initRecordFrom;
    private  String data;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getInitRecordFrom() {
        return initRecordFrom;
    }

    public void setInitRecordFrom(Integer initRecordFrom) {
        this.initRecordFrom = initRecordFrom;
    }
}
