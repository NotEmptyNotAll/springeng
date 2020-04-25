package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.EngineManufacturer;

import java.util.List;

public interface EngineManufactureService {
    List<String> getAllName();

    List<EngineManufacturer> getAllEngineManufacture();
}
