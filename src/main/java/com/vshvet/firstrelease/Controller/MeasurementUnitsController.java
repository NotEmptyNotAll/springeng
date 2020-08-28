package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Entity.MeasurementUnits;
import com.vshvet.firstrelease.Service.FuelTypeService;
import com.vshvet.firstrelease.Service.MeasurementUnitsService;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MeasurementUnitsController {
    @Autowired
    private MeasurementUnitsService  measurementUnitsService;


    @RequestMapping(value = "/measurementUnitsPagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", measurementUnitsService.getPaginationData(request));
            put("countResults", measurementUnitsService.getNumberOfPage(request));
        }};
    }

}
