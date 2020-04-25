package com.vshvet.firstrelease.Service;

import com.vshvet.firstrelease.Entity.AutoModel;

import java.util.List;

public interface AutoModelService {
    public List<AutoModel> getAutoModel();

    public List<String> getAllNameOfModel();

    public AutoModel findById(int id);
}
