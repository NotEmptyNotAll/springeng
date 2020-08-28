package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;
import java.util.Set;

public interface AutoModelService {
    List<DataByIdResponse> delete(Integer id);

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
