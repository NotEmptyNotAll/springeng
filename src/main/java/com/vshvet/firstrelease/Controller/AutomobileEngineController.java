package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.AutomobileEngineService;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import com.vshvet.firstrelease.payload.Request.ParametersPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AutomobileEngineController {

    @Autowired
    private AutomobileEngineService automobileEngineService;


    @RequestMapping(value = "/autoEngPagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", automobileEngineService.getPaginationData(request));
            put("countResults", automobileEngineService.getNumberOfPage(request));
        }};
    }

}
