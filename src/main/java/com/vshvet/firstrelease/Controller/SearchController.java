package com.vshvet.firstrelease.Controller;


import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.payload.Request.IdRequest;
import com.vshvet.firstrelease.payload.Request.EngineRequest;
import com.vshvet.firstrelease.payload.Response.AutomobileEngineResponse;
import com.vshvet.firstrelease.payload.Response.ElementsResponse;
import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.payload.Response.EngineResponse;
import com.vshvet.firstrelease.payload.Response.ParametersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * this controller is responsible for search requests
 *
 * */

@RestController
public class SearchController {


    private final ElementsService elementsService;

    private final AutomobileEngineService automobileEngineService;

    private final ParametrsService parametrsService;

    private  final  EngineNumberService engineNumberService;

    //returns a specific element
    @RequestMapping(value = "/getElements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ElementsResponse getElements(@RequestBody IdRequest elementsIdRequest) {
        return elementsService.getElements(elementsIdRequest.getId());
    }

    //returns the parameters of a specific element
    @RequestMapping(value = "/getParameters", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<ParametersResponse> getParameters(@RequestBody IdRequest elementsIdRequest) {
        return parametrsService.getParamByIdElem(elementsIdRequest.getId());
    }


    @RequestMapping(value = "/getAutoEng", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public EngineResponse getAutoEng(@RequestBody EngineRequest engineRequest) {
        return engineNumberService.getAutoEngByNumber(engineRequest);
    }


    //main query that returns engine information
    @RequestMapping(value = "/getAutoEngine", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getAutoEngineByParam(@RequestBody EngineRequest engine) {
        List<AutomobileEngineResponse> list= automobileEngineService.findByParam(engine);
        if(list==null){
            return new HashMap<String, Object> (){{
             put("listEng",list);
             put("status","недостатньо даних");
            }};
        }
        return list;
    }


    //autowired our service
    @Autowired
    public SearchController(AutomobileEngineService automobileEngineService,
                            ElementsService elementsService,
                            ParametrsService parametrsService,
                            EngineNumberService engineNumberService) {
        this.engineNumberService=engineNumberService;
        this.parametrsService = parametrsService;
        this.automobileEngineService = automobileEngineService;
        this.elementsService = elementsService;
    }

}
