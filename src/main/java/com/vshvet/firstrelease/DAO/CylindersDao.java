package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Cylinders;

import java.util.List;

public interface CylindersDao extends Dao<Cylinders> {
    List<String> getAllType();

}
