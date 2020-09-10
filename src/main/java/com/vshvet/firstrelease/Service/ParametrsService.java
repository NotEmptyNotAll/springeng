package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Parameters;
import com.vshvet.firstrelease.payload.Request.SaveOrUpdateParametersRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.ParamSizeNameResponse;
import com.vshvet.firstrelease.payload.Response.ParametersResponse;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

public interface ParametrsService {
    List<ParametersResponse> getParamByIdElem(Integer id, Integer auto_id);
    List<DataByIdResponse> delete(Integer id);

    void  save(Parameters parameter);

    Map<String, Object> getParamMap( AutomobileEngine automobileEngine);

    Boolean update(List<SaveOrUpdateParametersRequest> updateData);


    String save(List<SaveOrUpdateParametersRequest> saveData);
}
