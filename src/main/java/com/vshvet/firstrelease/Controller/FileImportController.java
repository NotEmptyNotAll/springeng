package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.Payload.Request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/import")
public class FileImportController {

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


    @RequestMapping(value = "/Cylinders", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtCylinders(@RequestBody ImprtDataRequest imprtData) {
        cylindersService.imprt(imprtData);
        return "";
    }

    @RequestMapping(value = "/EngManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtEngManufacture(@RequestBody ImprtDataRequest imprtData) {
        engineManufactureService.imprt(imprtData);
        return "";
    }

    @RequestMapping(value = "/FuelType", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtFuelType(@RequestBody ImprtDataRequest imprtData) {
        fuelTypeService.imprt(imprtData);
        return "";
    }

    @RequestMapping(value = "/AutoModel", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtAutoModel(@RequestBody ImprtDataRequest imprtData) {
        autoModelService.imprt(imprtData);
        return "";
    }

    @RequestMapping(value = "/AutoManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtAutoManufacture(@RequestBody ImprtDataRequest imprtData) {
        autoManufactureService.imprt(imprtData);
        return "";
    }

    @RequestMapping(value = "/SuperchargedType",
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtSuperchargedType(@RequestBody ImprtTwoDataRequest imprtData) {
        superchargedTypeService.imprt(imprtData);
        return "";
    }

    @RequestMapping(value = "/ParameterName", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtParameterName(@RequestBody ImprtTwoDataRequest imprtData) {
        parameterNameService.imprt(imprtData);
        return "";

    }

    @RequestMapping(value = "/MeasurementUnitsService", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtMeasurementUnits(@RequestBody ImprtTwoDataRequest imprtData) {
        measurementUnitsService.imprt(imprtData);
        return "";
    }

    @RequestMapping(value = "/AutoEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtAutoEngine(@RequestBody ImprtAutoEngineRequest imprtData) {
         automobileEngineService.imprt(imprtData);
        return "";
    }


    @RequestMapping(value = "/Engine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String imprtEngine(@RequestBody ImprtOrUpdateEngineRequest imprtData) {
         engineService.imprt(imprtData);
         return "";
    }

    /*

        @RequestMapping(value = "/Elements", //
                method = RequestMethod.POST, //
                produces = {MediaType.APPLICATION_JSON_VALUE, //
                        MediaType.APPLICATION_XML_VALUE})
        @ResponseBody
        public String imprtElements(@RequestBody imprtUpdateElemAndParamRequest imprtData) {
            elementsService.imprt(imprtData.getListElem());
            parametrsService.imprt(imprtData.getListimprtParam());
            parametrsService.update(imprtData.getListUpdateParam());
            return "ok";
        }


        @RequestMapping(value = "/AutoEngine", //
                method = RequestMethod.POST, //
                produces = {MediaType.APPLICATION_JSON_VALUE, //
                        MediaType.APPLICATION_XML_VALUE})
        @ResponseBody
        public String imprtAutoEngine(@RequestBody imprtAutoEngineRequest imprtData) {
            return automobileEngineService.imprt(imprtData);
        }


        @RequestMapping(value = "/EngNumber", //
                method = RequestMethod.POST, //
                produces = {MediaType.APPLICATION_JSON_VALUE, //
                        MediaType.APPLICATION_XML_VALUE})
        @ResponseBody
        public String imprtEngNumber(@RequestBody imprtTwoDataRequest imprtData) {
            return engineNumberService.imprt(imprtData);
        }


        @RequestMapping(value = "/Engine", //
                method = RequestMethod.POST, //
                produces = {MediaType.APPLICATION_JSON_VALUE, //
                        MediaType.APPLICATION_XML_VALUE})
        @ResponseBody
        public String imprtEngine(@RequestBody imprtOrUpdateEngineRequest imprtData) {
            return engineService.imprt(imprtData);
        }





    */
    @Autowired
    public FileImportController(AutomobileEngineService automobileEngineService,
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
