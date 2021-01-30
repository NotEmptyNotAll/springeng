package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.Payload.Request.SaveOrUpdateParametersRequest;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.Payload.Response.ParametersResponse;

import java.util.List;
import java.util.Map;

public interface ParametrsService {
    List<ParametersResponse> getParamByIdElem(Integer id, Integer auto_id);

    List<DataByIdResponse> delete(Integer id);

    void save(Parameters parameter);

    Boolean fastSave(List<SaveOrUpdateParametersRequest> saveData);

    Map<String, Object> getParamMap(AutomobileEngine automobileEngine);

    Boolean update(List<SaveOrUpdateParametersRequest> updateData);


    String save(List<SaveOrUpdateParametersRequest> saveData);
}
