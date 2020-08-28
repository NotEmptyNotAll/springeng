package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.payload.Request.FileListRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;

import java.util.List;

public interface FileStorageService {
    List<DataByIdResponse> delete(Integer id);

    void save(FileListRequest fileListRequest);
}
