package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.EngineManufacturer;

import java.util.List;

public interface EngineManufactureDao extends Dao<EngineManufacturer>{
    public List<String> getAllName() ;
    }
