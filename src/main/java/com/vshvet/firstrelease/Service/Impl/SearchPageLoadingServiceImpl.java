package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.Entity.*;
import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

@Service
public class SearchPageLoadingServiceImpl implements SearchPageLoadingService {

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

    private final SuperchargedTypeService superchargedTypeService;

    private final StatusService statusService;

    private Integer autoid;

    @Override
    @Transactional
    public DefaultDataResponse getCroppedDefaultData(EngineRequest engine) {
        DefaultDataResponse defaultDataResponse = new DefaultDataResponse();
        defaultDataResponse.setEngineType(engineService.getCroppedData(engine));
        defaultDataResponse.setAutoModel(autoModelService.getCroppedData(engine));
        defaultDataResponse.setEngineManufacture(autoManufactureService.getCroppedData(engine));
        defaultDataResponse.setFuelType(fuelTypeService.getCroppedData(engine));
        defaultDataResponse.setEngineNumber(engineNumberService.getCroppedData(engine));
        return defaultDataResponse;
    }

    @Override
    public void importExelFile() {

    }


    @Override
    @Transactional
    public Map<String, ?> getDefaultData() {
        return new HashMap<String, Object>() {{
            put("autoModel", autoModelService.getDataByIdResponse());
            put("fuelType", fuelTypeService.getDataByIdResponse());
            put("engineManufacture", autoManufactureService.getDataByIdResponse());
            put("engineType", engineService.getDataByIdResponse());
            put("engineNumber", engineNumberService.getDataByIdResponse());
        }};
    }

    @Override
    @Transactional
    public Map<Object, Object> getParamName() {
        return new HashMap<Object, Object>() {{
            put("paramName", parameterNameService.getAllNames());
            put("status", statusService.getAllData());
            put("units", measurementUnitsService.getAllUnits());

        }};
    }

    @Override
    @Transactional
    public AllAdditionalDataResponse getAllAdditionalData() {
        return new AllAdditionalDataResponse(
                autoModelService.getDataByIdResponse(),
                fuelTypeService.getDataByIdResponse(),
                engineManufactureService.getDataByIdResponse(),
                engineNumberService.getDataByIdResponse(),
                cylindersService.getDataByIdResponse(),
                superchargedTypeService.getDataByIdResponse(),
                autoManufactureService.getDataByIdResponse(),
                parameterNameService.getAllTreeRootName(),
                parameterNameService.getAllParameterSizeName(),
                engineService.getDataByIdResponse(),
                measurementUnitsService.getAllUnits(),
                automobileEngineService.getAllAuto()

        );
    }


    //autowired our service
    @Autowired
    public SearchPageLoadingServiceImpl(AutomobileEngineService automobileEngineService,
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
                                        SuperchargedTypeService superchargedTypeService,
                                        StatusService statusService) {
        this.statusService = statusService;
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

    @Override
    @Transactional
    public List<DataByIdResponse> getTreeRootName() {
        return new ArrayList<DataByIdResponse>() {{
            automobileEngineService.getAllAutoEngine().forEach(autoEng -> {
                add(new DataByIdResponse(autoEng.getAutoManufactureByAutoManufactureFk().getManufactureName()
                        + " mod." + autoEng.getAutoModelByAutoModelFk().getModelName() + " year "
                        + (autoEng.getReleaseYearFrom() != null ? autoEng.getReleaseYearFrom() : "")
                        + "-" + (autoEng.getReleaseYearBy() != null ? autoEng.getReleaseYearBy() : "")
                        , autoEng.getElemId()));
            });
        }};
    }

    @Override
    public List<AllParanNameResponse> getChildParamName() {
        return new ArrayList<AllParanNameResponse>() {{
            parameterNameService.getAllParametersName().forEach(elem -> {
                        add(new AllParanNameResponse(elem.getName(), elem.getId(), elem.getTreeRoot()));
                    }
            );
        }};
    }

    @Override
    public HashMap<String, Object> getTreeElements() {
        return new HashMap<String, Object>() {{
            put("elementsCh", elementsService.getTreeElements());
        }};
    }

}
    