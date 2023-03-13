package com.liuyubo.qs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyubo.qs.db.POJO.Site;
import com.liuyubo.qs.db.DAO.SiteMapper;
import com.liuyubo.qs.service.SiteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_site】的数据库操作Service实现
* @createDate 2023-03-13 21:40:59
*/
@Service
public class SiteServiceImlp implements SiteService {

    final SiteDao siteDao;

    public SiteServiceImlp(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @Override
    public ArrayList<HashMap> searchSiteByPage(HashMap param) {
        return siteDao.searchSiteByPage(param);
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




