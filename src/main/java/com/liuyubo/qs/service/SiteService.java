package com.liuyubo.qs.service;

import com.liuyubo.qs.db.POJO.Site;

import java.util.ArrayList;
import java.util.HashMap;

public interface SiteService {

    ArrayList<HashMap> searchSiteByPage(HashMap param);

    ArrayList<HashMap> searchAllSite();

    Integer insert(Site site);

    Integer update(Site site);

    Integer deleteSiteByIds(Integer[] ids);

    Long searchSiteCount(HashMap param);
}
