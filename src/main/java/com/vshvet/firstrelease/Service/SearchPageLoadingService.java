package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Payload.Request.EngineRequest;
import com.vshvet.firstrelease.Payload.Response.*;

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
