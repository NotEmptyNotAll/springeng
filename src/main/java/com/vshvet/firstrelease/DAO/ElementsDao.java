package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.payload.Request.ParamsRequest;

import java.util.List;

public interface ElementsDao extends Dao<Elements>{
    public List<Elements> findParentsElemByParam(ParamsRequest paramsRequest) throws ClassCastException ;

}
