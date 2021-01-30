package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.EngineManufacturer;
import com.vshvet.firstrelease.Entity.EngineNumber;
import com.vshvet.firstrelease.Payload.Request.EngineRequest;

import java.util.List;
import java.util.Set;

public interface EngineNumberDao extends Dao<EngineNumber> {
     AutomobileEngine getAutoEngByNum(Integer number);

    Set<EngineNumber> getCroppedByParamName(EngineRequest engineRequest);

     List<String> getAllName();
}
