package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.payload.Request.EngineRequest;

import java.util.List;

public interface AutomobileEngineDao extends Dao<AutomobileEngine> {
     List<AutomobileEngine> getAutoByParam(EngineRequest engineRequest);
}