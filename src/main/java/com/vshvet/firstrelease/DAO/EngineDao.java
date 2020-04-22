package com.vshvet.firstrelease.DAO;

import com.vshvet.firstrelease.Entity.Engine;

import java.util.List;

public interface EngineDao extends Dao<Engine> {
    public List<String> getAllType();
}
