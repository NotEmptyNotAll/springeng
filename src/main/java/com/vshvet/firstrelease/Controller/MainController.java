package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
* controller for returning start
* or data necessary for user search
* */
@RestController
public class MainController {

    private final EngineService engineService;

    private final FuelTypeService fuelTypeService;

    private final AutoModelService autoModelService;

    private final EngineManufactureService engineManufactureService;

    private final EngineNumberService engineNumberService;

    private final ParameterNameService parameterNameService;

    private final MeasurementUnitsService measurementUnitsService;

    //return a list of parameter names and units of measure per page
    @GetMapping("/paramNameAndUnits")
    public Map<String, Object> getParamName() {
        return new HashMap<String, Object>() {{
            put("paramName", parameterNameService.getAllNames());
            put("units", measurementUnitsService.getAllUnits());

        }};
    }
    @GetMapping("/")
    public String get() {
        return "I'm alive" ;
    }




    //return the initial data to the page
    @GetMapping("/start")
    public Map<String, ?> getDefaultData() {
        return new HashMap<String, Object>() {{
            put("autoModels", autoModelService.getAllNameOfModel());
            put("fuelType", fuelTypeService.getAllName());
            put("engineManufacture", engineManufactureService.getAllName());
            put("engineType", engineService.getAllType());
            put("engineNumber", engineNumberService.getAllNumber());
        }};
    }

    //autowired our service
    @Autowired
    public MainController(MeasurementUnitsService measurementUnitsService,EngineService engineService, FuelTypeService fuelTypeService, AutoModelService autoModelService, EngineManufactureService engineManufactureService, EngineNumberService engineNumberService, ParameterNameService parameterNameService) {
        this.engineService = engineService;
        this.fuelTypeService = fuelTypeService;
        this.autoModelService = autoModelService;
        this.engineManufactureService = engineManufactureService;
        this.engineNumberService = engineNumberService;
        this.parameterNameService = parameterNameService;
        this.measurementUnitsService=measurementUnitsService;
    }
}
