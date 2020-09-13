package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ElementsService {
    ElementsResponse getElements(Integer id);

    Elements findByParentIdAndParamNameFk(Integer paramFk,Integer parentId);

    void  save(Elements elements);

    List<String> getListFileUrlById(Integer id);

    List<ParamSizeNameResponse> getParametersSizeName(Integer id);

    List<TreeElementsResponse> getTreeElements();

    List<ElementsResponse> getAllRootElem();

    List<TreeToColumnsResponse> getTableColumn();

    Set<Integer> getParentElemId(List<ParamsRequest> request);

    String save(SaveOrUpdateElementsRequest saveData);

    List<Elements> getAllNodeOfTree();

    List<ElementsResponse> delete(Integer id);

    Integer getMaxId();

    List<DataByIdResponse> getDataByIdResponse();

    Boolean update(SaveOrUpdateElementsRequest updateData);

    Boolean update(List<SaveOrUpdateElementsRequest> updateData);


    List<AutomobileEngine> getParentElements(List<ParamsRequest> request);

    List<AutoEngineResponse> getParentElementsUpdate(EngineRequest request);

    List<ElementsResponse> getAllRootElemByAutoId(Integer id);

    void save(List<SaveOrUpdateElementsRequest> listElem, List<SaveOrUpdateParametersRequest> listSaveParam, List<SaveOrUpdateParametersRequest> listUpdateParam);

    void save(List<SaveOrUpdateElementsRequest> listElem);
}

