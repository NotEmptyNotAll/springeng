package com.vshvet.firstrelease.Payload.Request;

import java.util.List;

public class FileListRequest {

    private List<String> fileUrl;

    private Integer id;

    private Integer auto_id;

    public FileListRequest() {
    }

    public FileListRequest(List<String> fileUrl, Integer id, Integer auto_id) {
        this.fileUrl = fileUrl;
        this.id = id;
        this.auto_id = auto_id;
    }

    public List<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(List<String> fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(Integer auto_id) {
        this.auto_id = auto_id;
    }
}
