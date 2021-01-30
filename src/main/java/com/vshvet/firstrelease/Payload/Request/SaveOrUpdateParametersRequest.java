package com.vshvet.firstrelease.Payload.Request;

public class SaveOrUpdateParametersRequest {
    private int id;
    private int elemId;
    private int nameElemId;
    private int units;
    private Double doubleMin;
    private Double doubleMax;
    private Double doubleNum;
    private String textData;
    private Byte logic;
    private Integer auto_id;
    private Integer name;
    private String recordStatus;
    private String author;
    private String source;
    private Boolean editRow;
    private Integer status;

    public SaveOrUpdateParametersRequest() {
    }

    public SaveOrUpdateParametersRequest(
            Boolean editRow,
            int id,
            int elemId,
            int units,
            Double doubleMin,
            Double doubleMax,
            Double doubleNum,
            String textData,
            Byte logic,
            String recordStatus,
            String author,
            String source,
            Integer auto_id,
            int nameelemId,
            Integer name) {
        this.name=name;
        this.nameElemId = nameelemId;
        this.editRow = editRow;
        this.id = id;
        this.elemId = elemId;
        this.units = units;
        this.doubleMin = doubleMin;
        this.doubleMax = doubleMax;
        this.doubleNum = doubleNum;
        this.textData = textData;
        this.logic = logic;
        this.recordStatus = recordStatus;
        this.author = author;
        this.source = source;
        this.auto_id = auto_id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getNameElemId() {
        return nameElemId;
    }

    public void setNameElemId(int nameElemId) {
        this.nameElemId = nameElemId;
    }

    public Integer getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(Integer auto_id) {
        this.auto_id = auto_id;
    }

    public Boolean getEditRow() {
        return editRow;
    }

    public void setEditRow(Boolean editRow) {
        this.editRow = editRow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElemId() {
        return elemId;
    }

    public void setElemId(int elemId) {
        this.elemId = elemId;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
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

    public Byte getLogic() {
        return logic;
    }

    public void setLogic(Byte logic) {
        this.logic = logic;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
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
