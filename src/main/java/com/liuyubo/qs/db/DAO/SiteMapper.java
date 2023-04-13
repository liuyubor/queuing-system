package com.liuyubo.qs.db.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_site】的数据库操作Mapper
* @createDate 2023-03-13 21:40:59
* @Entity com.liuyubo.qs.db.POJO.Site
*/
@Repository
@Mapper
public interface SiteMapper {


    ArrayList<HashMap> searchAllSite();

    ArrayList<HashMap> searchSiteByPage(HashMap param);

    HashMap searchSiteInfoById(Integer id);

    Integer updateSiteCount(Integer id);

    ArrayList<HashMap> allSites(HashMap param);

    Integer addSite(HashMap map);
}




