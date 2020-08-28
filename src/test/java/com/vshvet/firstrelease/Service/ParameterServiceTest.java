package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Service.ParameterNameService;
import com.vshvet.firstrelease.payload.Response.ParamNameNodeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ParameterServiceTest {

    @Autowired
    private ParameterNameService parameterNameService;



    @Test
    void getAllParamTest() {
        List<ParamNameNodeResponse> paramNameNodeResponses = parameterNameService.getAllNames();
        System.out.println(paramNameNodeResponses.toString());
    }



}
