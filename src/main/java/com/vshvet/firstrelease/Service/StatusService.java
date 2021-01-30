package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;

public interface StatusService {
    List<DataByIdResponse> getAllData();
}
