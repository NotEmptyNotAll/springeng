package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.SuperchargedType;
import com.vshvet.firstrelease.Payload.Request.*;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;

public interface SuperchargedTypeService {
    List<String> getAllNameOfModel();

    List<DataByIdResponse> getPaginationData(PaginationDataRequest request);

    Integer getNumberOfPage(PaginationDataRequest request);

    Boolean update(UpdateTwoDataRequest updateData);

    List<DataByIdResponse> delete(Integer id);

    List<DataByIdResponse> getDataByIdResponse();


    List<SuperchargedType> getAllSuperchargedType();

    String save(SaveTwoDataRequest saveData);

    void imprt(ImprtTwoDataRequest imprtData);
}
