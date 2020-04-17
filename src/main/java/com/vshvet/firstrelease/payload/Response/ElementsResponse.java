package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Elements;

import java.util.ArrayList;
import java.util.List;

public class ElementsResponse {
    private Integer id;
    private String name;
    private List<ElementsResponse> elementsCh;

    public ElementsResponse(Elements elements){
        this.elementsCh=new ArrayList<ElementsResponse>(){{
            elements.getChildElements().forEach( elements ->
                    add(new ElementsResponse(elements))
            );
        }};
        this.name=elements.getParameterNamesByParamNameFk().getFullName();
        this.id=elements.getElemId();
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

    public List<ElementsResponse> getElementsCh() {
        return elementsCh;
    }

    public void setElementsCh(List<ElementsResponse> elementsCh) {
        this.elementsCh = elementsCh;
    }

}
