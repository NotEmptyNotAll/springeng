package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.ParamNameNodeResponse;

import java.util.List;

public interface ParameterNameService {
    List<ParamNameNodeResponse> getAllNames();
    List<DataByIdResponse> delete(Integer id);

    Integer getMaxId();

    void save(ParameterNames parameterNames);

    ParameterNames findByName(String name);

    List<DataByIdResponse> getDataByIdResponse();

    Boolean update(UpdateTwoDataRequest updateData);

    List<DataByIdResponse> getAllTreeRootName();
    List<DataByIdResponse> getAllParameterSizeName();

    List<ParameterNames> getAllParametersName();

    String save(SaveTwoDataRequest saveData);

    String save(SaveParameterNameRequest saveData);

    void imprt(ImprtTwoDataRequest imprtData);
}
