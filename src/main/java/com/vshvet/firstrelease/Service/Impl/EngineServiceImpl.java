package com.vshvet.firstrelease.Service.Impl;

import com.vshvet.firstrelease.DAO.Impl.AutomobileEngineDaoImpl;
import com.vshvet.firstrelease.DAO.EngineDao;
import com.vshvet.firstrelease.DAO.Impl.EngineDaoImpl;
import com.vshvet.firstrelease.Entity.Engine;
import com.vshvet.firstrelease.Exception.ObjectNotFoundException;
import com.vshvet.firstrelease.Service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    private EngineDao engineDao;

    @Override
    public Engine findById(int id) {
        engineDao.openCurrentSessionwithTransaction();
        Engine engine = engineDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("id : " + id));
        //engineDao.getByParam(new EngineRequest(engine));
        engineDao.closeCurrentSessionwithTransaction();
        return engine;
    }

    @Override
    public List<String> getAllType() {
        engineDao.openCurrentSessionwithTransaction();
        List<String> engines = engineDao.getAllType();
        engineDao.closeCurrentSessionwithTransaction();
        return engines;
    }

    @Override
    public List<Engine> getAll() {
        engineDao.openCurrentSessionwithTransaction();
        List<Engine> engines = engineDao.getAll();
        engineDao.closeCurrentSessionwithTransaction();
        return engines;
    }

    @Override
    public void update(Engine engine) {
        if (engine != null) {
            engineDao.openCurrentSessionwithTransaction();
            engineDao.update(engine);
            engineDao.closeCurrentSessionwithTransaction();
        }
    }

    @Override
    public void save(Engine engine) {
        if (engine != null) {
            engineDao.openCurrentSessionwithTransaction();
            engineDao.save(engine);
            engineDao.closeCurrentSessionwithTransaction();
        }
    }

    @Override
    public void delete(Engine engine) {
        if (engine != null) {
            engineDao.openCurrentSessionwithTransaction();
            engineDao.save(engine);
            engineDao.closeCurrentSessionwithTransaction();
        }
    }


}
