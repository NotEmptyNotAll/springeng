package com.vshvet.firstrelease.Payload.Response;

import com.vshvet.firstrelease.Entity.Elements;

import java.util.*;

public class ElementsResponse {
    private Integer id;
    private Integer param_name_id;
    private String name;
    private String color;
    private List<ElementsResponse> elementsCh;
    private Boolean parametersIsExistInChild;
    private Boolean paramIsNotEmpty;
    private Integer sortNumber;


    public ElementsResponse(Elements elements) {

        this.elementsCh = new ArrayList<ElementsResponse>() {{
            elements.getChildElements().forEach(elements -> {
                if (elements.getDate() == null) {
                    add(new ElementsResponse(elements));
                }
            });
        }};
        Collections.sort(elementsCh, Comparator.comparingInt(ElementsResponse::getSortNumber));
        this.color = elements.getColor();
        if (elements.getParamNameFk() == 1) {
            this.name = "val";
        } else {
            this.name = elements.getParameterNamesByParamNameFk().getFullName();
        }
        if (elements.getChildElements().size() > 0) {
            this.parametersIsExistInChild = true;
        } else {
            this.parametersIsExistInChild = false;
        }
        this.sortNumber = elements.getSortNumber();
        this.id = elements.getElemId();
        this.param_name_id = elements.getParamNameFk();
        //      if (elements.getParametersByElemId().size() > 0) {
        //        paramIsNotEmpty = true;
        //} else {
        //      paramIsNotEmpty = false;
        //}
    }

    public ElementsResponse(Elements elements, List<Integer> elemFkByAutoIdList) {
        this.elementsCh = new ArrayList<ElementsResponse>() {{
            elements.getChildElements().forEach(elements -> {
                if (elements.getDate() == null) {
                    add(new ElementsResponse(elements, elemFkByAutoIdList));
                }
            });
        }};
        Collections.sort(elementsCh, Comparator.comparingInt(ElementsResponse::getSortNumber));
        this.color = elements.getColor();
        this.name = elements.getParameterNamesByParamNameFk().getFullName();
        this.id = elements.getElemId();
        this.sortNumber = elements.getSortNumber();
        this.param_name_id = elements.getParamNameFk();
        this.parametersIsExistInChild = false;
        paramIsNotEmpty = this.existParamInChild(elements, elemFkByAutoIdList);
        //elements.getChildElements().forEach(
        //element -> {
        //if (element.getParametersByElemId() != null) {
        // if (element.getParametersByElemId().size() > 0) {
        //   element.getParametersByElemId().forEach(
        //         param -> {
        //           if (param.getAutoId() == auto_id)
        //             parametersIsExistInChild = true;
        //   });
        //   }
        //}
        //}
        //);
    }

    private boolean existParamInChild(Elements elements, List<Integer> elemFkByAutoIdList) {
        for (Elements e :
                elements.getChildElements()) {
            if (elemFkByAutoIdList.contains(e.getElemId())) {
                return true;
            }
        }
        return false;
    }

    public ElementsResponse(Elements elements, Integer auto_id) {
        this.elementsCh = new ArrayList<ElementsResponse>() {{
            elements.getChildElements().forEach(elements -> {
                if (elements.getDate() == null) {
                    add(new ElementsResponse(elements, auto_id));
                }
            });
        }};
        Collections.sort(elementsCh, Comparator.comparingInt(ElementsResponse::getSortNumber));
        this.color = elements.getColor();
        this.name = elements.getParameterNamesByParamNameFk().getFullName();
        this.id = elements.getElemId();
        this.sortNumber = elements.getSortNumber();
        this.param_name_id = elements.getParamNameFk();
        this.parametersIsExistInChild = false;
        //    if (elements.getParametersByElemId().size() > 0) {
        //      paramIsNotEmpty = true;
        //} else {
        //  paramIsNotEmpty = false;
        //}
        //elements.getChildElements().forEach(
        //element -> {
        //if (element.getParametersByElemId() != null) {
        // if (element.getParametersByElemId().size() > 0) {
        //   element.getParametersByElemId().forEach(
        //         param -> {
        //           if (param.getAutoId() == auto_id)
        //             parametersIsExistInChild = true;
        //   });
        //   }
        //}
        //}
        //);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementsResponse that = (ElementsResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(param_name_id, that.param_name_id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color) &&
                Objects.equals(elementsCh, that.elementsCh) &&
                Objects.equals(parametersIsExistInChild, that.parametersIsExistInChild) &&
                Objects.equals(paramIsNotEmpty, that.paramIsNotEmpty) &&
                Objects.equals(sortNumber, that.sortNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, param_name_id, name, color, elementsCh, parametersIsExistInChild, paramIsNotEmpty, sortNumber);
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
