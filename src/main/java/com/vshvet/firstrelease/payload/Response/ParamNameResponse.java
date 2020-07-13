package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.ParameterNames;

public class ParamNameResponse {

    private Integer id;

    private String name;

    private String fullName;
    private boolean tree_root;

    public ParamNameResponse() {
    }


    public ParamNameResponse(ParameterNames parameterNames, Integer id) {
        this.name = parameterNames.getName();
        this.fullName = parameterNames.getFullName();
        this.id = id;
    }

    public ParamNameResponse(ParameterNames parameterNames) {
        this.name = parameterNames.getName();
        this.fullName = parameterNames.getFullName();
        this.id = id;
        this.tree_root = parameterNames.getTreeRoot();
    }

    public ParamNameResponse(Integer id, String name, String fullName) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
    }


    public boolean isTree_root() {
        return tree_root;
    }

    public void setTree_root(boolean tree_root) {
        this.tree_root = tree_root;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
