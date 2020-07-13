package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Request.SaveOrUpdateParametersRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.ParametersResponse;

import java.util.List;

public interface ParametrsService {
    List<ParametersResponse> getParamByIdElem(Integer id, Integer auto_id);

    Boolean update(List<SaveOrUpdateParametersRequest> updateData);


    String save(List<SaveOrUpdateParametersRequest> saveData);
}
