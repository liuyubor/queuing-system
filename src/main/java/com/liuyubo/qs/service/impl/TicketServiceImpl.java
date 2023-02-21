package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.TicketDao;
import com.liuyubo.qs.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    final private TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Integer getCount(Integer siteId) {
        return ticketDao.getCount(siteId);
    }
}
