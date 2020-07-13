package com.vshvet.firstrelease.payload.Request;

import java.util.List;

public class SaveUpdateElemAndParamRequest {
    private List<SaveOrUpdateElementsRequest> listElem;
    private List<SaveOrUpdateParametersRequest> listSaveParam;
    private List<SaveOrUpdateParametersRequest> listUpdateParam;

    public SaveUpdateElemAndParamRequest(List<SaveOrUpdateElementsRequest> listElem,
                                         List<SaveOrUpdateParametersRequest> listSaveParam,
                                         List<SaveOrUpdateParametersRequest> listUpdateParam) {
        this.listElem = listElem;
        this.listSaveParam = listSaveParam;
        this.listUpdateParam = listUpdateParam;
    }

    public SaveUpdateElemAndParamRequest() {
    }

    public List<SaveOrUpdateElementsRequest> getListElem() {
        return listElem;
    }

    public void setListElem(List<SaveOrUpdateElementsRequest> listElem) {
        this.listElem = listElem;
    }

    public List<SaveOrUpdateParametersRequest> getListSaveParam() {
        return listSaveParam;
    }

    public void setListSaveParam(List<SaveOrUpdateParametersRequest> listSaveParam) {
        this.listSaveParam = listSaveParam;
    }

    public List<SaveOrUpdateParametersRequest> getListUpdateParam() {
        return listUpdateParam;
    }

    public void setListUpdateParam(List<SaveOrUpdateParametersRequest> listUpdateParam) {
        this.listUpdateParam = listUpdateParam;
    }
}
