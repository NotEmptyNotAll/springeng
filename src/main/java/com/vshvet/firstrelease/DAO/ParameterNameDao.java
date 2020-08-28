package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.ParameterNames;

import java.util.List;

public interface ParameterNameDao extends Dao<ParameterNames> {
    List<ParameterNames> getAllTreeRootName();

    List<ParameterNames> getAllParameterSizeName();

    ParameterNames findByName(String name);

    Integer getMaxId();
}
