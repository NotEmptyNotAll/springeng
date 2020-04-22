package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutoModel;

import java.util.List;

public interface AutoModelDao  extends  Dao<AutoModel>{
    public List<String> getAllNameOfModel();

}
