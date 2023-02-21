package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.SiteDao;
import com.liuyubo.qs.db.POJO.Site;
import com.liuyubo.qs.service.SiteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SiteServiceImlp implements SiteService {

    final SiteDao siteDao;

    public SiteServiceImlp(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @Override
    public ArrayList<HashMap> searchAllSite() {
        return siteDao.searchAllSite();
    }

    @Override
    public Integer insert(Site site) {
        return siteDao.insert(site);
    }

    @Override
    public Integer update(Site site) {
        return siteDao.update(site);
    }

    @Override
    public Integer deleteSiteByIds(Integer[] ids) {
        return siteDao.deleteSiteByIds(ids);
    }

    @Override
    public Long searchSiteCount(HashMap param) {
        return siteDao.searchSiteCount(param);
    }
}
