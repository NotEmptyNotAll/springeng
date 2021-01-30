package com.vshvet.firstrelease.Payload.Response;

public class AllParanNameResponse {
    private String data;
    private Integer id;
    private boolean tree_node;

    public AllParanNameResponse(String data, Integer id, boolean tree_node) {
        this.data = data;
        this.id = id;
        this.tree_node = tree_node;
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

    public boolean isTree_node() {
        return tree_node;
    }

    public void setTree_node(boolean tree_node) {
        this.tree_node = tree_node;
    }
}
