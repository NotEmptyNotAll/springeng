package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Parameters;

public class ParametersResponse {
    private Integer id;
    private Integer name;
    private Integer units;
    private Double doubleMin;
    private Double doubleMax;
    private Double doubleNum;
    private String textData;
    private String author;
    private String source;
    private Boolean editRow;
    private Integer status;
    private Integer select;


    public ParametersResponse(Parameters parameters) {
        this.units = parameters.getMeasurementUnitsByMeasurementUnitsFk().getId();
        this.doubleMin = parameters.getDoubleMin();
        this.doubleMax = parameters.getDoubleMax();
        this.doubleNum = parameters.getDoubleNum();
        this.textData = parameters.getTextData();
        this.author = parameters.getAuthor();
        this.source = parameters.getSource();
        this.name = parameters.getElementsByElemFk().getParameterNamesByParamNameFk().getId();
        this.id = parameters.getParamId();
        this.editRow = false;
        this.status = parameters.getStatus().getId();
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEditRow() {
        return editRow;
    }

    public void setEditRow(Boolean editRow) {
        this.editRow = editRow;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getDoubleMin() {
        return doubleMin;
    }

    public void setDoubleMin(Double doubleMin) {
        this.doubleMin = doubleMin;
    }

    public Double getDoubleMax() {
        return doubleMax;
    }

    public void setDoubleMax(Double doubleMax) {
        this.doubleMax = doubleMax;
    }

    public Double getDoubleNum() {
        return doubleNum;
    }

    public void setDoubleNum(Double doubleNum) {
        this.doubleNum = doubleNum;
    }

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
