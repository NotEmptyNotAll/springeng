package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.payload.Request.EngineRequest;

import java.util.List;
import java.util.Set;

public interface EngineManufactureDao extends Dao<EngineManufacturer>{
     List<String> getAllName();

     Set<EngineManufacturer> getCroppedByParamName(EngineRequest engineRequest);

}
