package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.ReservationMapper;
import com.liuyubo.qs.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author kdrsi
 * @description 针对表【tb_reservation】的数据库操作Service实现
 * @createDate 2023-03-13 21:40:59
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    @Override
    public Integer insert(HashMap map) {
        return reservationMapper.insert(map);
    }
}




