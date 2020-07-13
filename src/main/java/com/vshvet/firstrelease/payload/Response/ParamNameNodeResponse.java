package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Elements;

import java.util.ArrayList;
import java.util.List;

public class ParamNameNodeResponse {

    private Integer id;

    private String name;

    private List<ParamNameResponse> paramNameResponseList;

    public ParamNameNodeResponse() {
    }

    public ParamNameNodeResponse(Elements elements) {
        this.id = elements.getParameterNamesByParamNameFk().getId();
        this.name = elements.getParameterNamesByParamNameFk().getName();
        this.paramNameResponseList = new ArrayList<ParamNameResponse>() {{
            elements.getChildElements().forEach(elem -> {
                add(new ParamNameResponse(elem.getParameterNamesByParamNameFk(),elem.getElemId()));
            });
        }};
    }

    public ParamNameNodeResponse(Integer id,
                                 String name,
                                 List<ParamNameResponse> paramNameResponseList) {
        this.id = id;
        this.name = name;
        this.paramNameResponseList = paramNameResponseList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParamNameResponse> getParamNameResponseList() {
        return paramNameResponseList;
    }

    public void setParamNameResponseList(List<ParamNameResponse> paramNameResponseList) {
        this.paramNameResponseList = paramNameResponseList;
    }
}
