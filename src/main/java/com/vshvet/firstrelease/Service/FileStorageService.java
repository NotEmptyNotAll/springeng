package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Payload.Request.FileListRequest;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;

import java.util.List;

public interface FileStorageService {
    List<DataByIdResponse> delete(Integer id);

    void save(FileListRequest fileListRequest);
}
