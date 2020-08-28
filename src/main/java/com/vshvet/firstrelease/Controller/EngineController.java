package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.AutoModelService;
import com.vshvet.firstrelease.Service.EngineService;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EngineController {


    @Autowired
    private EngineService engineService;


    @RequestMapping(value = "/enginePagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", engineService.getPaginationData(request));
            put("countResults", engineService.getNumberOfPage(request));
        }};
    }

}
