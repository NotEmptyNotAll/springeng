package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.Payload.Request.IdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/delete")
public class DeleteController {


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
    public final FileStorageService fileStorageService;


    @RequestMapping(value = "/elements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> elements(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", elementsService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/paramName", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> paramName(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", parameterNameService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/parameters", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> parameters(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", parametrsService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/autoModel", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> autoModel(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", autoModelService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/engine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> engine(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", engineService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/autoEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> autoEngine(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", automobileEngineService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/measurementUnits", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> measurementUnits(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", measurementUnitsService.delete(elementsIdRequest.getId()));
        }};
    }


    @RequestMapping(value = "/cylinders", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> cylinders(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", cylindersService.delete(elementsIdRequest.getId()));
        }};
    }


    @RequestMapping(value = "/fuelType", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> fuelType(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", fuelTypeService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/engineManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> engineManufacture(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", engineManufactureService.delete(elementsIdRequest.getId()));
        }};
    }

    @RequestMapping(value = "/autoManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> autoManufacture(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", autoManufactureService.delete(elementsIdRequest.getId()));
        }};
    }


    @RequestMapping(value = "/fileStorage", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> fileStorage(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", fileStorageService.delete(elementsIdRequest.getId()));
        }};
    }


    @RequestMapping(value = "/supercharged", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> supercharged(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listDependencyElements", superchargedTypeService.delete(elementsIdRequest.getId()));
        }};
    }

    @Autowired
    public DeleteController(AutomobileEngineService automobileEngineService,
                            ElementsService elementsService,
                            ParametrsService parametrsService,
                            EngineNumberService engineNumberService,
                            EngineManufactureService engineManufactureService,
                            MeasurementUnitsService measurementUnitsService,
                            EngineService engineService,
                            FuelTypeService fuelTypeService,
                            AutoModelService autoModelService,
                            ParameterNameService parameterNameService,
                            FileStorageService fileStorageService,
                            CylindersService cylindersService,
                            AutoManufactureService autoManufactureService,
                            SuperchargedTypeService superchargedTypeService) {
        this.fileStorageService = fileStorageService;
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

