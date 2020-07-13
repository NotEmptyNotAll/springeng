package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Elements;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ElementsResponse {
    private Integer id;
    private Integer param_name_id;
    private String name;
    private List<ElementsResponse> elementsCh;
    private Boolean parametersIsExistInChild;
    private Boolean paramIsNotEmpty;

    public ElementsResponse(Elements elements) {
        this.elementsCh = new ArrayList<ElementsResponse>() {{
            elements.getChildElements().forEach(elements -> {
                        if (elements.getChildElements().size() > 0)
                            add(new ElementsResponse(elements));
                    }
            );
        }};
        this.name = elements.getParameterNamesByParamNameFk().getFullName();
        this.id = elements.getElemId();
        this.parametersIsExistInChild = false;
        this.param_name_id = elements.getParamNameFk();
        if (elements.getElemId() == 166) {
            System.out.println('l');
        }

        if (elements.getParametersByElemId().size() > 0) {
            paramIsNotEmpty = true;
        } else {
            paramIsNotEmpty = false;
        }
    }

    public ElementsResponse(Elements elements, Integer auto_id) {
        this.elementsCh = new ArrayList<ElementsResponse>() {{
            elements.getChildElements().forEach(elements -> {
                        if (elements.getChildElements().size() > 0)
                            add(new ElementsResponse(elements, auto_id));
                    }
            );
        }};
        this.name = elements.getParameterNamesByParamNameFk().getFullName();
        this.id = elements.getElemId();
        this.param_name_id = elements.getParamNameFk();
        this.parametersIsExistInChild = false;
        if (elements.getParametersByElemId().size() > 0) {
            paramIsNotEmpty = true;
        } else {
            paramIsNotEmpty = false;
        }
        elements.getChildElements().forEach(
                element -> {
                    if (element.getParametersByElemId() != null) {
                        element.getParametersByElemId().forEach(
                                param -> {
                                    if (param.getAutoId() == auto_id)
                                        parametersIsExistInChild = true;
                                });
                    }
                }
        );
    }


    public Integer getParam_name_id() {
        return param_name_id;
    }

    public void setParam_name_id(Integer param_name_id) {
        this.param_name_id = param_name_id;
    }

    public Boolean getParamIsNotEmpty() {
        return paramIsNotEmpty;
    }

    public void setParamIsNotEmpty(Boolean paramIsNotEmpty) {
        this.paramIsNotEmpty = paramIsNotEmpty;
    }

    public Boolean getParametersIsExistInChild() {
        return parametersIsExistInChild;
    }

    public void setParametersIsExistInChild(Boolean parametersIsExistInChild) {
        this.parametersIsExistInChild = parametersIsExistInChild;
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
