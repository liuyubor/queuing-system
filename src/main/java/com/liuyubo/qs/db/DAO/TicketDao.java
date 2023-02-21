package com.liuyubo.qs.db.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TicketDao {

    Integer getCount(Integer siteId);
}
