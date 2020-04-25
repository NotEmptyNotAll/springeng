package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.FuelType;

import java.util.List;

public interface FuelTypeService {
    List<String> getAllName();
    List<FuelType> getAllFuelType();
}
