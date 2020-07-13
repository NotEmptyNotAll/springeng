package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;

public interface CylindersService {
    String save(SaveDataRequest saveData);

    List<Cylinders> getAllCylinders();

    Boolean update(UpdateDataRequest updateData);


    List<DataByIdResponse> getDataByIdResponse();

    void imprt(ImprtDataRequest imprtDataRequest);

    List<String> getAllNameOfCylinders();
}
