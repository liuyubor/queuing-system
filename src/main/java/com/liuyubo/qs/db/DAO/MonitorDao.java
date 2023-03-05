package com.liuyubo.qs.db.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface MonitorDao {
    Integer insert(HashMap param);

    Integer deleteByIds(HashMap param);

    Integer update(HashMap param);

    ArrayList<HashMap> searchAllMonitor();
}
