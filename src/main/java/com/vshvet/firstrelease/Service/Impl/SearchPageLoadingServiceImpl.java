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


    private FuelType saveFuelTypeByRow(XSSFRow row) {
        FuelType fuelType = new FuelType();
        fuelType.setStatus(new Status(2));
        fuelType.setNameType(getTextFromCell(row.getCell(4)));
        fuelTypeService.save(fuelType);
        return fuelType;
    }

    private AutoManufacture saveAutoManuf(XSSFRow row) {
        AutoManufacture autoManufacture = new AutoManufacture();
        autoManufacture.setStatus(new Status(2));
        autoManufacture.setManufactureName(getTextFromCell(row.getCell(0)));
        autoManufactureService.save(autoManufacture);
        return autoManufacture;
    }

    private EngineManufacturer saveEngineManuf(XSSFRow row) {
        EngineManufacturer engineManufacturer = new EngineManufacturer();
        engineManufacturer.setStatus(new Status(2));
        engineManufacturer.setNameManufacturer(getTextFromCell(row.getCell(8)));
        engineManufactureService.save(engineManufacturer);
        return engineManufacturer;
    }

    private AutoModel saveAutoModel(XSSFRow row) {
        AutoModel autoModel = new AutoModel();
        autoModel.setStatus(new Status(2));
        autoModel.setModelName(getTextFromCell(row.getCell(1)));
        autoModelService.save(autoModel);
        return autoModel;
    }


    private Engine saveEngine(XSSFRow row) {

        Engine engine = new Engine();
        engine.setStatus(new Status(2));
        engine.setCylindersByCylindersPlacementFk(new Cylinders(100));
        engine.setSuperchargedTypeBySuperchargedTypeFk(new SuperchargedType(1));
        if (getTextFromCell(row.getCell(2)) != null) {
            engine.setEngineType(getTextFromCell(row.getCell(2)));
        } else {
            engine.setEngineType("NA");
        }
        if (getTextFromCell(row.getCell(4))!=null) {
            FuelType fuelType = fuelTypeService.findByName(getTextFromCell(row.getCell(4)));
            if (fuelType != null) {
                engine.setFuelTypeByFuelTypeFk(fuelType);
            } else {
                engine.setFuelTypeByFuelTypeFk(saveFuelTypeByRow(row));
            }
        } else {
            engine.setFuelTypeByFuelTypeFk(new FuelType());
        }
        if (getTextFromCell(row.getCell(8)) != null) {
            EngineManufacturer engineManufacturer = engineManufactureService.findByName(getTextFromCell(row.getCell(8)));
            if (engineManufacturer != null) {
                engine.setEngineManufacturerByEngineManufacturerFk(engineManufacturer);
            } else {
                engine.setEngineManufacturerByEngineManufacturerFk(saveEngineManuf(row));
            }
        } else {
            engine.setEngineManufacturerByEngineManufacturerFk(new EngineManufacturer(1));
        }
        String tempCell = getTextFromCell(row.getCell(6));
        if (tempCell != null) {
            String[] str = tempCell.split("x");
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            try {
                if (str.length > 1) {
                    Double d = 0d;
                    double d1 = 0d;
                    Number number = format.parse(str[0]);
                    Number number1 = format.parse(str[1]);
                    d = number.doubleValue();
                    d1 = number1.doubleValue();
                    engine.setPistonDiameter(d);
                    engine.setPistonStroke((int) d1);
                } else {
                    Double d = 0d;
                    Number number = format.parse(str[0]);
                    d = number.doubleValue();
                    engine.setPistonDiameter(d);
                }
            } catch (ParseException ignored) {
            }
        }
        engine.setPowerKwt(getTextFromCell(row.getCell(3)));
        engine.setEngineCapacity((int) (row.getCell(7).getNumericCellValue() * 1000));
        try {
            engineService.save(engine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return engine;
    }


    private AutomobileEngine saveAutoEngineByRow(XSSFRow row) {
        AutomobileEngine automobileEngine = new AutomobileEngine();
        automobileEngine.setStatus(new Status(2));

        if (getTextFromCell(row.getCell(1)) != null) {
            AutoModel autoModel = autoModelService.findByName(getTextFromCell(row.getCell(1)));
            if (autoModel != null) {
                automobileEngine.setAutoModelByAutoModelFk(autoModel);
            } else {
                automobileEngine.setAutoModelByAutoModelFk(saveAutoModel(row));
            }
        } else {
            automobileEngine.setAutoModelByAutoModelFk(new AutoModel(1));
        }
        if (getTextFromCell(row.getCell(0)) != null) {
            AutoManufacture autoManufacture = autoManufactureService.findByName(getTextFromCell(row.getCell(0)));
            if (autoManufacture != null) {
                automobileEngine.setAutoManufactureByAutoManufactureFk(autoManufacture);
            } else {
                automobileEngine.setAutoManufactureByAutoManufactureFk(saveAutoManuf(row));
            }
        } else {
            automobileEngine.setAutoManufactureByAutoManufactureFk(new AutoManufacture(1));
        }

        Engine engine = engineService.findByName(getTextFromCell(row.getCell(2)));
        if (engine != null) {
            automobileEngine.setEngineByEngineFk(engine);
        } else {
            automobileEngine.setEngineByEngineFk(saveEngine(row));
        }

        if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
            automobileEngine.setYears(getTextFromCell(row.getCell(5)));
        }
        try {
            if (row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                automobileEngine.setReleaseYearFrom((int) row.getCell(5).getNumericCellValue());
            }
        } catch (IllegalStateException e) {
        }

        automobileEngineService.save(automobileEngine);
        return automobileEngine;
    }


    private boolean existAutoEnginInRow(XSSFRow row) {
        return getTextFromCell(row.getCell(0)) != null ||
                getTextFromCell(row.getCell(1)) != null ||
                getTextFromCell(row.getCell(2)) != null ||
                getTextFromCell(row.getCell(3)) != null ||
                getTextFromCell(row.getCell(4)) != null ||
                getTextFromCell(row.getCell(5)) != null ||
                getTextFromCell(row.getCell(6)) != null ||
                getTextFromCell(row.getCell(7)) != null ||
                getTextFromCell(row.getCell(8)) != null;
    }

    private int getIdElemByNumber(int number) {
        switch (number) {
            case 9:
                return 196;
            case 10:
                return 193;
            case 11:
                return 195;
            case 12:
                return 194;
            case 13:
                return 165;
            case 15:
                return 164;
            case 16:
                return 802;
            case 17:
                return 168;
            case 18:
                return 169;
            case 19:
                return 806;
            case 20:
                return 198;
            case 22:
                return 197;
            case 23:
                return 199;
            case 24:
                return 201;
            case 25:
                return 804;
        }
        return -1;
    }

    private boolean isNotEmptyRow(XSSFRow row) {
        return this.existAutoEnginInRow(row) ||
                getTextFromCell(row.getCell(9)) != null ||
                getTextFromCell(row.getCell(10)) != null ||
                getTextFromCell(row.getCell(11)) != null ||
                getTextFromCell(row.getCell(12)) != null ||
                getTextFromCell(row.getCell(13)) != null ||
                getTextFromCell(row.getCell(14)) != null ||
                getTextFromCell(row.getCell(15)) != null ||
                getTextFromCell(row.getCell(16)) != null ||
                getTextFromCell(row.getCell(17)) != null ||
                getTextFromCell(row.getCell(18)) != null ||
                getTextFromCell(row.getCell(19)) != null ||
                getTextFromCell(row.getCell(20)) != null ||
                getTextFromCell(row.getCell(21)) != null ||
                getTextFromCell(row.getCell(22)) != null ||
                getTextFromCell(row.getCell(23)) != null ||
                getTextFromCell(row.getCell(24)) != null ||
                getTextFromCell(row.getCell(25)) != null ||
                getTextFromCell(row.getCell(26)) != null;

    }

    private int saveElement(Integer parentId, Integer paramFk) {
        Elements elements = new Elements();
        elements.setParameterNamesByParamNameFk(new ParameterNames(paramFk));
        elements.setParentElements(new Elements(parentId));
        elements.setElemId(elementsService.getMaxId() + 1);
        elements.setStatus(new Status(2));
        elements.setSortNumber(elementsService.getMaxId() + 1);
        elements.setColor("#FFFFFF");
        elementsService.save(elements);

        return elements.getElemId();
    }

    private int getElemIByNameAndParentId(String name, Integer parentId) {
        ParameterNames parameterNames = parameterNameService.findByName(name);
        int elemId = -1;
        if (parameterNames == null) {
            if (name != null) {
                parameterNames = new ParameterNames();
                parameterNames.setFullName(name);
                parameterNames.setName(name);
                parameterNames.setStatus(new Status(2));
                parameterNames.setTreeRoot(true);
                parameterNameService.save(parameterNames);
                parameterNames = parameterNameService.findByName(name);
                int paramFk = parameterNames.getId();
                elemId = saveElement(parentId, paramFk);
            } else {
                int paramFk = 1;
                Elements elements = elementsService.findByParentIdAndParamNameFk(paramFk, parentId);
                if (elements == null) {
                    elemId = saveElement(parentId, paramFk);
                } else {
                    elemId = elements.getElemId();
                }
            }
        } else {
            int paramFk = parameterNames.getId();
            Elements elements = elementsService.findByParentIdAndParamNameFk(paramFk, parentId);
            if (elements != null) {
                elemId = elements.getElemId();
            } else {
                elemId = saveElement(parentId, paramFk);
            }
        }
        return elemId;

    }


    private void setParamValueByStr(Parameters parameters, String str) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Double d1 = 0d;
            Double d2 = 0d;
            String[] arrStr = str.split("-");
            if (arrStr.length > 2) {
                parameters.setTextData(str);
            } else if (arrStr.length == 2) {
                try {
                    Number number = format.parse(arrStr[0]);
                    d1 = number.doubleValue();
                    Number number1 = format.parse(arrStr[1]);
                    d2 = number1.doubleValue();
                } catch (ParseException e) {
                }
                parameters.setDoubleMin(d1);
                parameters.setDoubleMax(d2);
            } else {
                try {
                    Number number = format.parse(str);
                    d1 = number.doubleValue();
                } catch (ParseException e) {
                }
                parameters.setDoubleNum(d1);
            }
        } catch (NumberFormatException e) {
            parameters.setTextData(str);
        }

    }

    private void addParamToAutoEng(Integer autoid, XSSFRow row) {
        try {


            for (int j = 9; j < 26; j++) {
                String value = getTextFromCell(row.getCell(j));
                if (value != null && j != 14 && j != 21) {
                    Parameters parameters = new Parameters();
                    parameters.setAutoId(autoid);
                    setParamValueByStr(parameters, value);
                    parameters.setMeasurementUnitsByMeasurementUnitsFk(new MeasurementUnits(5));
                    parameters.setElementsByElemFk(new Elements(getElemIByNameAndParentId(getTextFromCell(row.getCell(14)),
                            this.getIdElemByNumber(j))));
                    parameters.setStatus(new Status(2));
                    parameters.setAuthor(getTextFromCell(row.getCell(26)));
                    parameters.setStatus(new Status(2));
                    parametrsService.save(parameters);
                }
            }}catch (Exception e){

        }
    }

    private String getTextFromCell(XSSFCell cell) {
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        }

        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return null;
    }

    @Transactional
    private void saveRow(XSSFRow row) {
        if (this.isNotEmptyRow(row)) {
            if (this.existAutoEnginInRow(row)) {
                AutomobileEngine automobileEngine = automobileEngineService.findByNames(
                        getTextFromCell(row.getCell(1)),
                        getTextFromCell(row.getCell(2)),
                        getTextFromCell(row.getCell(0)),
                        getTextFromCell(row.getCell(5)));
                if (automobileEngine == null) {
                    autoid = saveAutoEngineByRow(row).getId();
                } else {
                    autoid = automobileEngine.getId();
                }
                addParamToAutoEng(autoid, row);
            } else {
                addParamToAutoEng(autoid, row);
            }
        }
    }

    @Override
    public void importExelFile() {
        XSSFWorkbook myExcelBook = null;
        try {
            myExcelBook = new XSSFWorkbook(new FileInputStream("/home/gtkf-3959/project/enginefinal/tehinfo.xlsx"));
            this.autoid = -1;
            XSSFSheet myExcelSheet = myExcelBook.getSheet("к.вал");
            for (int i = 75000; i < 100000; i++) {
                System.out.println("///");
                System.out.println(i);
                System.out.println("///");
                saveRow(myExcelSheet.getRow(i));
            }
            myExcelBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                null,
                measurementUnitsService.getAllUnits(),
                null

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
    