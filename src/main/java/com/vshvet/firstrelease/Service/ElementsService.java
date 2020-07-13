package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.*;

import java.util.List;
import java.util.Set;

public interface ElementsService {
    ElementsResponse getElements(Integer id);

    List<TreeElementsResponse> getTreeElements();

    Set<Integer> getParentElemId(EngineRequest request);


    String save(SaveOrUpdateElementsRequest saveData);

    List<Elements> getAllNodeOfTree();

    Integer getMaxId();

    List<DataByIdResponse> getDataByIdResponse();

    Boolean update(SaveOrUpdateElementsRequest updateData);


    List<AutomobileEngineResponse> getParentElements(EngineRequest request);

    List<AutoEngineResponse> getParentElementsUpdate(EngineRequest request);

    List<ElementsResponse> getAllRootElemByAutoId(Integer id);

    void save(List<SaveOrUpdateElementsRequest> listElem, List<SaveOrUpdateParametersRequest> listSaveParam, List<SaveOrUpdateParametersRequest> listUpdateParam);

    void save(List<SaveOrUpdateElementsRequest> listElem);
}
