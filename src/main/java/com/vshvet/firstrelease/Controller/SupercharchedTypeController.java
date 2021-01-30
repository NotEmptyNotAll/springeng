package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.SuperchargedTypeService;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SupercharchedTypeController {


    @Autowired
    private SuperchargedTypeService superchargedTypeService;


    @RequestMapping(value = "/superchargedTypePagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", superchargedTypeService.getPaginationData(request));
            put("countResults", superchargedTypeService.getNumberOfPage(request));
        }};
    }



}
