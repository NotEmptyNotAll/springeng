package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.Entity.SuperchargedType;
import com.vshvet.firstrelease.payload.Request.*;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;

public interface SuperchargedTypeService {
    List<String> getAllNameOfModel();

    Boolean update(UpdateTwoDataRequest updateData);

    List<DataByIdResponse> getDataByIdResponse();


    List<SuperchargedType> getAllSuperchargedType();

    String save(SaveTwoDataRequest saveData);

    void imprt(ImprtTwoDataRequest imprtData);
}
