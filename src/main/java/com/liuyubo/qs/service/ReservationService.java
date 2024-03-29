package com.liuyubo.qs.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_reservation】的数据库操作Service
* @createDate 2023-03-13 21:40:59
*/
public interface ReservationService {

    Integer insert(HashMap map);

    Boolean selectTimeConflict(HashMap map);

    ArrayList<HashMap> selectAllReserve();

    HashMap selectReserveByUserId(String userId);

    ArrayList<HashMap> allReserves(HashMap param);

    Integer deleteReserve(String id);

    Integer updateReserve(HashMap map);
}
