package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.payload.Response.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SearchPageLoadingService {
    DefaultDataResponse getCroppedDefaultData(EngineRequest engine);


    void importExelFile();


     Map<String, ?> getDefaultData();

     Map<Object, Object> getParamName();

    AllAdditionalDataResponse getAllAdditionalData();

    List<DataByIdResponse> getTreeRootName();

    List<AllParanNameResponse> getChildParamName();

    HashMap<String, Object> getTreeElements();


}
