package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.EngineNumber;

import java.util.List;

public interface EngineNumberDao extends Dao<EngineNumber> {
    public AutomobileEngine getAutoEngByNum(String number);

    public List<String> getAllName();
}
