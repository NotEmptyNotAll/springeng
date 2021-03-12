package com.vshvet.firstrelease.Payload.Response;

public class TranslateParamResponse {
    private String data;
    private Integer id;
    private Integer langId;

    public TranslateParamResponse() {
    }

    public TranslateParamResponse(String data, Integer id, Integer langId) {
        this.data = data;
        this.id = id;
        this.langId = langId;
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

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }
}
