package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.FileStorageDao;
import com.vshvet.firstrelease.Entity.AutomobileEngine;
import com.vshvet.firstrelease.Entity.Elements;
import com.vshvet.firstrelease.Entity.FileStorage;
import com.vshvet.firstrelease.Service.FileStorageService;
import com.vshvet.firstrelease.Payload.Request.FileListRequest;
import com.vshvet.firstrelease.Payload.Response.DataByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileStorageDao fileStorageDao;

    @Override
    @Transactional
    public List<DataByIdResponse> delete(Integer id) {
        FileStorage fileStorage = fileStorageDao.findById(id).get();
        this.fileStorageDao.delete(fileStorage);
        return null;
    }

    @Override
    @Transactional
    public void save(FileListRequest fileListRequest) {
        AutomobileEngine automobileEngine = new AutomobileEngine(fileListRequest.getAuto_id());
        Elements elements = new Elements(fileListRequest.getId());
        fileListRequest.getFileUrl().forEach(elem -> {
            try {
                fileStorageDao.save(new FileStorage(elements, automobileEngine, elem));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
