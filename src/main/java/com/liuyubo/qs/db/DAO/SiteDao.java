package com.liuyubo.qs.db.DAO;

import com.liuyubo.qs.db.POJO.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface SiteDao {

    long searchSiteCount(HashMap param);

    ArrayList<HashMap> searchSiteByPage(HashMap param);

    ArrayList<HashMap> searchAllSite();

    Integer insert(Site site);

    Integer update(Site site);

    Integer deleteSiteByIds(Integer[] ids);

}
