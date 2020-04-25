package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Response.ParametersResponse;

import java.util.List;

public interface ParametrsService {
    List<ParametersResponse> getParamByIdElem(Integer id);
}
