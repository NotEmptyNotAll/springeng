package com.vshvet.firstrelease.Service;


import com.vshvet.firstrelease.Entity.AutoManufacture;
import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;


public interface AutoManufactureService {

    AutoManufacture findByName(String name);
    void save(AutoManufacture autoManufacture);
    List<DataByIdResponse> delete(Integer id);

    AutoManufacture findById(Integer id);

    List<String> getAllManufacture();

    Boolean update(UpdateDataRequest updateData);

    List<DataByIdResponse> getDataByIdResponse();

    List<AutoManufacture> getAllAutoManufacture();

    String save(SaveDataRequest saveData);

    void imprt(ImprtDataRequest imprtData);

    List<DataByIdResponse> getCroppedData(EngineRequest engine);
}
