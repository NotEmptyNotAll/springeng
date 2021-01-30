package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.Payload.Request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateController {

    private final ElementsService elementsService;

    private final AutomobileEngineService automobileEngineService;

    private final ParametrsService parametrsService;

    private final EngineNumberService engineNumberService;

    private final EngineService engineService;

    private final FuelTypeService fuelTypeService;

    private final AutoModelService autoModelService;

    private final EngineManufactureService engineManufactureService;

    private final ParameterNameService parameterNameService;

    private final MeasurementUnitsService measurementUnitsService;

    private final AutoManufactureService autoManufactureService;

    private final CylindersService cylindersService;

    public final SuperchargedTypeService superchargedTypeService;


    @RequestMapping(value = "/updateEngManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateEngManufacture(@RequestBody UpdateDataRequest updateData) {
        return engineManufactureService.update(updateData);
    }


    @RequestMapping(value = "/updateAutoEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateAutoEngine(@RequestBody List<UpdateAutoEngineRequest> updateData) {
        return automobileEngineService.update(updateData);
    }


    @RequestMapping(value = "/updateEngNumber", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateEngNumber(@RequestBody UpdateTwoDataRequest updateData) {
        return engineNumberService.update(updateData);
    }


    @RequestMapping(value = "/updateElements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateElements(@RequestBody SaveOrUpdateElementsRequest updateData) {
        return elementsService.update(updateData);
    }


    @RequestMapping(value = "/updateEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateEngine(@RequestBody SaveOrUpdateEngineRequest updateData) {
        return engineService.update(updateData);
    }

    @RequestMapping(value = "/updateFuelType", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateFuelType(@RequestBody UpdateDataRequest updateData) {
        return fuelTypeService.update(updateData);
    }


    @RequestMapping(value = "/updateAutoModel", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateAutoModel(@RequestBody UpdateDataRequest updateData) {
        return autoModelService.update(updateData);
    }


    @RequestMapping(value = "/updateParameterName", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateParameterName(@RequestBody UpdateTwoDataRequest updateData) {
        return parameterNameService.update(updateData);
    }

    @RequestMapping(value = "/updateMeasurementUnitsService", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateMeasurementUnitsService(@RequestBody UpdateTwoDataRequest updateData) {
        return measurementUnitsService.update(updateData);
    }

    @RequestMapping(value = "/updateAutoManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateAutoManufacture(@RequestBody UpdateDataRequest updateData) {
        return autoManufactureService.update(updateData);
    }

    @RequestMapping(value = "/updateCylinders", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateCylinders(@RequestBody UpdateDataRequest updateData) {
        return cylindersService.update(updateData);
    }

    @RequestMapping(value = "/updateSuperchargedType",
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Boolean updateSuperchargedType(@RequestBody UpdateTwoDataRequest updateData) {
        return superchargedTypeService.update(updateData);
    }

    @Autowired
    public UpdateController(AutomobileEngineService automobileEngineService,
                            ElementsService elementsService,
                            ParametrsService parametrsService,
                            EngineNumberService engineNumberService,
                            EngineManufactureService engineManufactureService,
                            MeasurementUnitsService measurementUnitsService,
                            EngineService engineService,
                            FuelTypeService fuelTypeService,
                            AutoModelService autoModelService,
                            ParameterNameService parameterNameService,
                            CylindersService cylindersService,
                            AutoManufactureService autoManufactureService,
                            SuperchargedTypeService superchargedTypeService) {
        this.superchargedTypeService = superchargedTypeService;
        this.cylindersService = cylindersService;
        this.autoManufactureService = autoManufactureService;
        this.engineManufactureService = engineManufactureService;
        this.engineNumberService = engineNumberService;
        this.parametrsService = parametrsService;
        this.automobileEngineService = automobileEngineService;
        this.elementsService = elementsService;
        this.engineService = engineService;
        this.fuelTypeService = fuelTypeService;
        this.autoModelService = autoModelService;
        this.parameterNameService = parameterNameService;
        this.measurementUnitsService = measurementUnitsService;

    }
}
