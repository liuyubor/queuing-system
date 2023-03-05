package com.liuyubo.qs.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface MonitorService {
    Integer insert(HashMap param);

    Integer deleteByIds(HashMap param);

    Integer update(HashMap param);

    ArrayList<HashMap> searchAllMonitor();
}
