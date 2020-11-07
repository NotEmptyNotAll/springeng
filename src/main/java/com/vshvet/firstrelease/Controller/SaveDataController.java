package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.payload.Request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/save")
public class SaveDataController {

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


    @RequestMapping(value = "/saveEngManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveEngManufacture(@RequestBody SaveDataRequest saveData) {
        return engineManufactureService.save(saveData);
    }

    @RequestMapping(value = "/saveParamElements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveParamElements(@RequestBody SaveUpdateElemAndParamRequest saveData) {
        //   elementsService.save(saveData.getListElem(),saveData.getListSaveParam(),saveData.getListUpdateParam());
        parametrsService.save(saveData.getListSaveParam());
        parametrsService.update(saveData.getListUpdateParam());
        return "ok";
    }


    @RequestMapping(value = "/saveFileData", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveFileData(@RequestBody FileListRequest saveData) {
        fileStorageService.save(saveData);
        return "ok";
    }

    @RequestMapping(value = "/saveElements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveElements(@RequestBody SaveUpdateElemAndParamRequest saveData) {
        elementsService.save(saveData.getListElem());
        elementsService.update(saveData.getListUpdateElem());
        return "ok";
    }

    @RequestMapping(value = "/fastSaveAutoEng", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String fastSaveAutoEng(@RequestBody FastAutoEngineSaveOrUpdateRequest saveData) {
          //  parametrsService.fastSave(saveData);
        return "ok";
    }

    @RequestMapping(value = "/fastSaveParam", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String fastSaveParam(@RequestBody List<SaveOrUpdateParametersRequest> saveData) {
        parametrsService.fastSave(saveData);
        return "ok";
    }

    @RequestMapping(value = "/saveAutoEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveAutoEngine(@RequestBody SaveAutoEngineRequest saveData) {
        return automobileEngineService.save(saveData);
    }


    @RequestMapping(value = "/saveEngNumber", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveEngNumber(@RequestBody SaveTwoDataRequest saveData) {
        return engineNumberService.save(saveData);
    }


    @RequestMapping(value = "/saveEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveEngine(@RequestBody SaveOrUpdateEngineRequest saveData) {
        return engineService.save(saveData);
    }

    @RequestMapping(value = "/saveFuelType", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveFuelType(@RequestBody SaveDataRequest saveData) {
        return fuelTypeService.save(saveData);
    }


    @RequestMapping(value = "/saveAutoModel", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveAutoModel(@RequestBody SaveDataRequest saveData) {
        return autoModelService.save(saveData);
    }


    @RequestMapping(value = "/saveParameterName", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveParameterName(@RequestBody SaveTwoDataRequest saveData) {
        return parameterNameService.save(saveData);
    }

    @RequestMapping(value = "/saveMeasurementUnitsService", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveMeasurementUnits(@RequestBody SaveTwoDataRequest saveData) {
        return measurementUnitsService.save(saveData);
    }

    @RequestMapping(value = "/saveAutoManufacture", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveAutoManufacture(@RequestBody SaveDataRequest saveData) {
        return autoManufactureService.save(saveData);
    }

    @RequestMapping(value = "/saveCylinders", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveCylinders(@RequestBody SaveDataRequest saveData) {
        return cylindersService.save(saveData);
    }

    @RequestMapping(value = "/saveSuperchargedType",
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String saveSuperchargedType(@RequestBody SaveTwoDataRequest saveData) {
        return superchargedTypeService.save(saveData);
    }

    @Autowired
    public SaveDataController(AutomobileEngineService automobileEngineService,
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
                              FileStorageService fileStorageService,
                              SuperchargedTypeService superchargedTypeService) {
        this.superchargedTypeService = superchargedTypeService;
        this.fileStorageService = fileStorageService;
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
