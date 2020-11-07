package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ColumnResponse {
    private String name;
    private String color;
    private String id;
    private Integer sortNumber;
    private Integer nameId;

    private List<ColumnResponse> columnList;

    public ColumnResponse() {
    }

    public ColumnResponse(List<ColumnResponse> columnList) {
        this.columnList = columnList;
    }

    public ColumnResponse(Elements elements) {
        this.name = elements.getParameterNamesByParamNameFk().getName();
        this.id = Integer.toString(elements.getElemId());
        this.columnList = new ArrayList<>();
        this.color = elements.getColor();
        this.sortNumber=elements.getSortNumber();
        this.nameId=elements.getParamNameFk();
        if (elements.getChildElements().size() > 0) {
            elements.getChildElements().forEach(elem -> {
                if (elem.getDate() == null) {
                    this.columnList.add(new ColumnResponse(elem));
                }
            });
            Collections.sort(columnList, Comparator.comparingInt(ColumnResponse::getSortNumber));
        }
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        sortNumber = sortNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ColumnResponse(String id) {
        this.id = id;
    }

    public List<ColumnResponse> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnResponse> columnList) {
        this.columnList = columnList;
    }

    public ColumnResponse(String name, String elemId) {
        this.id = elemId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
