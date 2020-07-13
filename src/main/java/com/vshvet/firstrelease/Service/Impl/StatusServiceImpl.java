package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.StatusDao;
import com.vshvet.firstrelease.Service.StatusService;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusDao statusDao;


    @Override
    public List<DataByIdResponse> getAllData() {
        return new ArrayList<DataByIdResponse>() {{
            statusDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getStatus(), elem.getId()));
            });
        }};
    }
}
