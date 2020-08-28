package com.vshvet.firstrelease.Controller;


import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.payload.Request.IdRequest;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Request.ParametersPageRequest;
import com.vshvet.firstrelease.payload.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * this controller is responsible for search requests
 * */

@RestController
public class SearchController {


    private final ElementsService elementsService;

    private final AutomobileEngineService automobileEngineService;

    private final ParametrsService parametrsService;

    private final EngineNumberService engineNumberService;

    private final SearchPageLoadingService searchPageLoadingService;
    private final EngineService engineService;

    //returns a specific element
    @RequestMapping(value = "/getElements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getElements(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("name", automobileEngineService.getNameAuto(elementsIdRequest.getId()));
            put("maxId", elementsService.getMaxId() + 1);
            put("elementsCh", elementsService.getAllRootElemByAutoId(elementsIdRequest.getId()));
        }};
    }


    @RequestMapping(value = "/getElementsAndMaxId", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getElementsAndMaxId(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("maxId", automobileEngineService.getNameAuto(elementsIdRequest.getId()));
            put("elementsCh", elementsService.getAllRootElemByAutoId(elementsIdRequest.getId()));
        }};
    }


    //returns the parameters of a specific element
    @RequestMapping(value = "/getParameters", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<ParametersResponse> getParameters(@RequestBody IdRequest elementsIdRequest) {
        return parametrsService.getParamByIdElem(elementsIdRequest.getId(), elementsIdRequest.getAuto_id());
    }


    @RequestMapping(value = "/getFileUrlById", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getFileUrlById(@RequestBody IdRequest elementsIdRequest) {
        return new HashMap<String, Object>() {{
            put("listUrl", elementsService.getListFileUrlById(elementsIdRequest.getId()));
        }};
    }


    @RequestMapping(value = "/getParametersSizeName", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<ParamSizeNameResponse> getParametersSizeName(@RequestBody IdRequest elementsIdRequest) {
        return elementsService.getParametersSizeName(elementsIdRequest.getId());
    }


    @RequestMapping(value = "/getAutoEng", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public EngineResponse getAutoEng(@RequestBody EngineRequest engineRequest) {
        return engineNumberService.getAutoEngByNumber(engineRequest);
    }


    @RequestMapping(value = "/getEng", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public FullEngineResponse getEng(@RequestBody IdRequest idRequest) {
        return engineService.getEngineResponse(idRequest.getId());
    }

    @RequestMapping(value = "/getAutoEngineById", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public AutoEngineResponse getAutoEngineById(@RequestBody IdRequest idRequest) {
        return automobileEngineService.getById(idRequest.getId());
    }

    //main query that returns engine information
    @RequestMapping(value = "/getAutoEngineForUpdate", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getAutoEngineByParam(@RequestBody EngineRequest engine) {
        List<AutoEngineResponse> list = automobileEngineService.findByParamForUpdate(engine);
        if (list == null) {
            return new HashMap<String, Object>() {{
                put("listEng", list);
                put("status", "недостатньо даних");
            }};
        }
        return list;
    }

    @RequestMapping(value = "/getAutoEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getAutoEngine(@RequestBody EngineRequest engine) {
        List<AutomobileEngineResponse> list = automobileEngineService.findByParam(engine);
        if (list == null) {
            return new HashMap<String, Object>() {{
                put("listEng", list);
                put("status", "недостатньо даних");
            }};
        }
        return list;
    }

    @RequestMapping(value = "/getAllAutoEngAndParam", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getAllAutoEngAndParam(@RequestBody ParametersPageRequest request) {
        return new HashMap<String, Object>() {{
            put("engineData", automobileEngineService.getAllAutoEngAndParam(request));
            put("countResults", automobileEngineService.getNumberOfPageByParam(request));
            put("columnParam", elementsService.getTableColumn());
        }};
    }

    // query that returns cropped data(Engine type, fuel type and other)  by information of request
    @RequestMapping(value = "/getCroppedStartData", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getCroppedStartData(@RequestBody EngineRequest engine) {
        return searchPageLoadingService.getCroppedDefaultData(engine);
    }

    //autowired our service
    @Autowired
    public SearchController(AutomobileEngineService automobileEngineService,
                            ElementsService elementsService,
                            ParametrsService parametrsService,
                            EngineNumberService engineNumberService,
                            SearchPageLoadingService searchPageLoadingService,
                            EngineService engineService) {
        this.engineService = engineService;
        this.engineNumberService = engineNumberService;
        this.parametrsService = parametrsService;
        this.automobileEngineService = automobileEngineService;
        this.elementsService = elementsService;
        this.searchPageLoadingService = searchPageLoadingService;
    }

}
