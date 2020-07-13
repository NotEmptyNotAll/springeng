package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StatusService {
    List<DataByIdResponse> getAllData();
}
