package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.CylindersDao;
import com.vshvet.firstrelease.Entity.Cylinders;
import com.vshvet.firstrelease.Entity.Status;
import com.vshvet.firstrelease.Service.CylindersService;
import com.vshvet.firstrelease.payload.Request.ImprtDataRequest;
import com.vshvet.firstrelease.payload.Request.SaveDataRequest;
import com.vshvet.firstrelease.payload.Request.UpdateDataRequest;
import com.vshvet.firstrelease.payload.Response.DataByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CylindersServiceImpl implements CylindersService {

    @Autowired
    private CylindersDao cylindersDao;

    @Override
    @Transactional
    public String save(SaveDataRequest saveData) {
        try {
            Cylinders cylinders = new Cylinders();
            cylinders.setStatus(new Status(saveData.getStatus()));
            cylinders.setTypeName(saveData.getSaveData());
            cylindersDao.save(cylinders);
            return "збереження було успішним";
        } catch (Exception e) {
            return "збереження не вдалося, спробуйте ще раз";
        }
    }

    @Override
    @Transactional
    public void imprt(ImprtDataRequest imprtDataRequest) {
        imprtDataRequest.getList().forEach(this::save);
    }


    @Override
    @Transactional
    public List<Cylinders> getAllCylinders() {
        List<Cylinders> all = cylindersDao.getAll();
        return all;
    }

    @Override
    @Transactional
    public Boolean update(UpdateDataRequest updateData) {
        try {
            Cylinders newCylinders = cylindersDao.findById(updateData.getObjToBeChanged()).get();
            Cylinders oldCylinders = new Cylinders(newCylinders);
            newCylinders.setStatus(new Status(updateData.getStatus()));
            newCylinders.setTypeName(updateData.getUpdateData());
            newCylinders.setId(updateData.getObjToBeChanged());
            oldCylinders.setDate(new Date(new java.util.Date().getTime()));
            cylindersDao.update(newCylinders, oldCylinders);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    @Override
    @Transactional
    public List<DataByIdResponse> getDataByIdResponse() {
        List<DataByIdResponse> all = new ArrayList<DataByIdResponse>() {{
            cylindersDao.getAll().forEach(elem -> {
                add(new DataByIdResponse(elem.getTypeName(),
                        elem.getId(), elem.getStatus().getStatus()));
            });
        }};

        return all;
    }

    @Override
    @Transactional
    public List<String> getAllNameOfCylinders() {
        List<String> allType = cylindersDao.getAllType();
        return allType;
    }
}
