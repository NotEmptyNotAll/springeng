package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.Parameters;

import java.util.ArrayList;
import java.util.List;

public class TreeElementsResponse {
    private Integer id;
    private String name;
    private List<TreeElementsResponse> elementsCh;


    public TreeElementsResponse() {
    }

    public TreeElementsResponse(Elements elements) {
            this.elementsCh = new ArrayList<TreeElementsResponse>() {{
                elements.getChildElements().forEach(elements -> {
                            add(new TreeElementsResponse(elements));
                        }
                );
            }};
            this.id = elements.getElemId();
            this.name = elements.getParameterNamesByParamNameFk().getName();

    }
    public TreeElementsResponse(Parameters parameters) {
        this.name =parameters.getElementsByElemFk().getParameterNamesByParamNameFk().getName();
        this.id = parameters.getElemFk();
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

    public List<TreeElementsResponse> getElementsCh() {
        return elementsCh;
    }

    public void setElementsCh(List<TreeElementsResponse> elementsCh) {
        this.elementsCh = elementsCh;
    }
}
