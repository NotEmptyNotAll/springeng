package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.Engine;

import java.util.List;

public interface EngineService {
    Engine findById(int id);

    List<String> getAllType();

    List<Engine> getAll();

    void update(Engine engine);

    void save(Engine engine);

    void delete(Engine engine);
}
