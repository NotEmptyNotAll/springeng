package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;

import java.util.List;

public interface CylindersDao extends Dao<Cylinders> {
    List<String> getAllType();

    List<Cylinders> getPagination(PaginationDataRequest request);

    Long getCountResults(PaginationDataRequest request);

}
