package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Entity.ParameterNames;
import com.vshvet.firstrelease.Service.ParameterNameService;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ParameterNameController {
    @Autowired
    private ParameterNameService parameterNameService;


    @RequestMapping(value = "/parameterNamePagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", parameterNameService.getPaginationData(request));
            put("countResults", parameterNameService.getNumberOfPage(request));
        }};
    }

    @RequestMapping(value = "/parameterNameSizePagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPaginationParamSize(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", parameterNameService.getPaginationDataParamSize(request));
            put("countResults", parameterNameService.getNumberOfPageParamSize(request));
        }};
    }

}
