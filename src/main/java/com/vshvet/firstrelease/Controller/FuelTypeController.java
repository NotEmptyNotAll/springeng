package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Entity.FuelType;
import com.vshvet.firstrelease.Service.EngineService;
import com.vshvet.firstrelease.Service.FuelTypeService;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FuelTypeController {
    @Autowired
    private FuelTypeService fuelTypeService;


    @RequestMapping(value = "/fuelTypePagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", fuelTypeService.getPaginationData(request));
            put("countResults", fuelTypeService.getNumberOfPage(request));
        }};
    }

}
