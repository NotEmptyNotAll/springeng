package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.EngineNumber;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import com.vshvet.firstrelease.payload.Response.EngineResponse;

import java.util.List;
import java.util.Set;

public interface EngineNumberService {
    List<String> getAllNumber();

    List<DataByIdResponse> delete(Integer id);


    List<DataByIdResponse> getCroppedData(EngineRequest engineRequest);

    String save(SaveTwoDataRequest saveData);

    List<EngineNumber> getAllEngineNumber();
    Boolean update(UpdateTwoDataRequest updateData);

    List<DataByIdResponse> getDataByIdResponse();


    EngineResponse getAutoEngByNumber(EngineRequest engineRequest);
}
