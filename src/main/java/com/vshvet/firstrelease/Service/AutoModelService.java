package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;

public interface AutoModelService {
    List<DataByIdResponse> delete(Integer id);

    List<DataByIdResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);


    List<AutoModel> getAutoModel();

    void save(AutoModel autoModel);
    AutoModel findByName(String name);

    List<DataByIdResponse> getCroppedData(EngineRequest engineRequest);

    List<DataByIdResponse> getDataByIdResponse();

    Boolean update(UpdateDataRequest updateData);

    String save(SaveDataRequest saveData);

    List<String> getAllNameOfModel();

    AutoModel findById(int id);

    void imprt(ImprtDataRequest imprtData);
}
