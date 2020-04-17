package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.ParameterNames;

public class ParamNameResponse {

    private String name;

    private String fullName;

    public ParamNameResponse() {
    }


    public ParamNameResponse(ParameterNames parameterNames) {
        this.name = parameterNames.getName();
        this.fullName = parameterNames.getFullName();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
