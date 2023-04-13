package com.liuyubo.qs.service;

import com.liuyubo.qs.db.POJO.Site;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_site】的数据库操作Service
* @createDate 2023-03-13 21:40:59
*/
public interface SiteService {

    ArrayList<HashMap> searchSiteByPage(HashMap param);

    ArrayList<HashMap> searchAllSite();

    Integer insert(Site site);

    Integer update(Site site);

    Integer deleteSiteByIds(Integer[] ids);

    Long searchSiteCount(HashMap param);

    HashMap searchSiteInfoById(Integer id);

    Integer updateSiteCount(Integer id);

    String searchDequeueCountById(Integer id);

    ArrayList<HashMap> allSites(HashMap param);
}
