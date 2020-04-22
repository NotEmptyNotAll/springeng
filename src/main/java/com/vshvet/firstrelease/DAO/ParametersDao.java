package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Parameters;

public interface ParametersDao extends Dao<Parameters> {
    public Parameters findParamByElemId(Integer id);
    }
