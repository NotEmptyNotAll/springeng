package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.*;
import com.vshvet.firstrelease.Service.Impl.*;
import com.vshvet.firstrelease.payload.Request.IdRequest;
import com.vshvet.firstrelease.payload.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * controller for returning start
 * or data necessary for user search
 * */
@RestController
public class MainController {


    private final SearchPageLoadingService searchPageLoadingService;

    //return a list of parameter names and units of measure per page
    @GetMapping("/paramNameAndUnits")
    public Map<Object, Object> getParamName() {
        return searchPageLoadingService.getParamName();
    }

    @GetMapping("/")
    public String get() {
        return "I'm alive";
    }


    @GetMapping("/getTreeElements")
    public Map<String, Object> getTreeElements() {
        return searchPageLoadingService.getTreeElements();
    }


    //return the initial data to the page
    // data presented in map
    @GetMapping("/start")
    public Map<String, ?> getDefaultData() {
        return searchPageLoadingService.getDefaultData();
    }


    @GetMapping("/lolkek")
    public String lolkek() {
        this.searchPageLoadingService.importExelFile();
        return "ok";
    }

    @GetMapping("/getAllAdditionalData")
    public AllAdditionalDataResponse getAllAdditionalData() {
        return searchPageLoadingService.getAllAdditionalData();
    }

    @GetMapping("/getAllParamName")
    public List<AllParanNameResponse> getChildParamName() {
        return searchPageLoadingService.getChildParamName();
    }


    @GetMapping("/getTreeRootName")
    public List<DataByIdResponse> getTreeRootName() {
        return searchPageLoadingService.getTreeRootName();
    }


    //autowired our service
    @Autowired
    public MainController(SearchPageLoadingService searchPageLoadingService) {
        this.searchPageLoadingService = searchPageLoadingService;

    }
}
