package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.MonitorDao;
import com.liuyubo.qs.service.MonitorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MonitorServiceImpl implements MonitorService {

    private final MonitorDao monitorDao;

    public MonitorServiceImpl(MonitorDao monitorDao) {
        this.monitorDao = monitorDao;
    }

    public Integer insert(HashMap param){
        return monitorDao.insert(param);
    }
}
