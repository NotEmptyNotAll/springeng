package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.payload.Request.EngineRequest;

import java.util.List;
import java.util.Set;

public interface AutoModelDao  extends  Dao<AutoModel>{
    List<String> getAllNameOfModel();
AutoModel findByName(String name);
    Set<AutoModel> getCroppedModel(EngineRequest engineRequest);

}
