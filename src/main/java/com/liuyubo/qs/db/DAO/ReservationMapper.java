package com.liuyubo.qs.db.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_reservation】的数据库操作Mapper
* @createDate 2023-03-13 21:40:59
* @Entity com.liuyubo.qs.db.POJO.Reservation
*/
@Repository
@Mapper
public interface ReservationMapper {

    Integer insert(HashMap map);

    Integer selectTimeConflict(HashMap map);

    ArrayList<HashMap> selectAllReserve();

    HashMap selectReserveByUserId(String userId);
}




