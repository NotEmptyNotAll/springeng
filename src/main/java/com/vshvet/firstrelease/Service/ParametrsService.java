package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Payload.Request.FastParamSaveRequest;
import com.vshvet.firstrelease.Payload.Request.SaveOrUpdateParametersRequest;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.Payload.Response.ParametersResponse;
import com.vshvet.firstrelease.Payload.Response.TranslateParamResponse;
import com.vshvet.firstrelease.Payload.Response.TwoDataByIdResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ParametrsService {
    List<ParametersResponse> getParamByIdElem(Integer id, Integer auto_id);

    List<DataByIdResponse> delete(Integer id);

    void save(Parameters parameter);

    Boolean fastSave(FastParamSaveRequest saveData);

    Map<String, Object> getParamMap(AutomobileEngine automobileEngine, Integer langId);

    List<TranslateParamResponse>  getParamTranslateById(Integer id, Integer autoId);

    Boolean update(List<SaveOrUpdateParametersRequest> updateData);


    String save(List<SaveOrUpdateParametersRequest> saveData);
}
