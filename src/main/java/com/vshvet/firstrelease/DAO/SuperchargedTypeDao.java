package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.SuperchargedType;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;

import java.util.List;

public interface SuperchargedTypeDao extends Dao<SuperchargedType> {
    List<String> getAllType();

    List<SuperchargedType> getPagination(PaginationDataRequest request);

    Long getCountResults(PaginationDataRequest request);

}
