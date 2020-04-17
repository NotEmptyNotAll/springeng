package com.vshvet.firstrelease.payload.Response;

import com.vshvet.firstrelease.Entity.Parameters;

public class ParametersResponse {
    private String name;
    private String measurementUnits;
    private Double doubleMin;
    private Double doubleMax;
    private Double doubleNum;
    private String textData;
    private String author;
    private String source;


    public ParametersResponse(Parameters parameters ) {
        this.measurementUnits = parameters.getMeasurementUnitsByMeasurementUnitsFk().getShortNameM();
        this.doubleMin = parameters.getDoubleMin();
        this.doubleMax = parameters.getDoubleMax();
        this.doubleNum = parameters.getDoubleNum();
        this.textData = parameters.getTextData();
        this.author = parameters.getAuthor();
        this.source = parameters.getSource();
        this.name= parameters.getElementsByElemFk().getParameterNamesByParamNameFk().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurementUnits() {
        return measurementUnits;
    }

    public void setMeasurementUnits(String measurementUnits) {
        this.measurementUnits = measurementUnits;
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
