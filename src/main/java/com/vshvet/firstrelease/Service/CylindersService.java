package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.Payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.Payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.Payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;

public interface CylindersService {
    List<DataByIdResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);

    List<DataByIdResponse> delete(Integer id);

    String save(SaveDataRequest saveData);

    List<Cylinders> getAllCylinders();

    Boolean update(UpdateDataRequest updateData);


    List<DataByIdResponse> getDataByIdResponse();

    void imprt(ImprtDataRequest imprtDataRequest);

    List<String> getAllNameOfCylinders();
}
