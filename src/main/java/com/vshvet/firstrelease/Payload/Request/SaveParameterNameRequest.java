package com.vshvet.firstrelease.Payload.Request;

public class SaveParameterNameRequest {
    private String name;
    private String fullName;
    private boolean root;

    public SaveParameterNameRequest() {
    }

    public SaveParameterNameRequest(String name, String fullName, boolean root) {
        this.name = name;
        this.fullName = fullName;
        this.root = root;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
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
