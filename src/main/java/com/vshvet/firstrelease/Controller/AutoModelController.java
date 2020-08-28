package com.vshvet.firstrelease.Controller;

import com.vshvet.firstrelease.Entity.AutoModel;
import com.vshvet.firstrelease.Service.AutoModelService;
import com.vshvet.firstrelease.Service.AutomobileEngineService;
import com.vshvet.firstrelease.payload.Request.PaginationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AutoModelController {

    @Autowired
    private AutoModelService autoModelService;


    @RequestMapping(value = "/autoModelPagination", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Map<String, Object> getPagination(@RequestBody PaginationDataRequest request) {
        return new HashMap<String, Object>() {{
            put("data", autoModelService.getPaginationData(request));
            put("countResults", autoModelService.getNumberOfPage(request));
        }};
    }

}
