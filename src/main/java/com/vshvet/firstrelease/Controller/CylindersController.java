package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Service.CylindersService;
import com.vshvet.firstrelease.Payload.Request.PaginationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CylindersController {

    @Autowired
    private CylindersService cylindersService;


    @RequestMapping(value = "/cylindersPagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", cylindersService.getPaginationData(request));
            put("countResults", cylindersService.getNumberOfPage(request));
        }};
    }

}
