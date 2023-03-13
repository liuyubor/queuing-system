package com.liuyubo.qs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyubo.qs.db.POJO.Reservation;
import com.liuyubo.qs.service.ReservationService;
import com.liuyubo.qs.db.DAO.ReservationMapper;
import org.springframework.stereotype.Service;

/**
* @author kdrsi
* @description 针对表【tb_reservation】的数据库操作Service实现
* @createDate 2023-03-13 21:40:59
*/
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation>
    implements ReservationService {

}




