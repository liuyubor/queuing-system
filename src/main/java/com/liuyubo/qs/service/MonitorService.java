package com.liuyubo.qs.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_monitor】的数据库操作Service
* @createDate 2023-03-13 21:40:59
*/
public interface MonitorService {
    Integer insert(HashMap param);

    Integer deleteByIds(HashMap param);

    Integer update(HashMap param);

    ArrayList<HashMap> searchAllMonitor();
}
