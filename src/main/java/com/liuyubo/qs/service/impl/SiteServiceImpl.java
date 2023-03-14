package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.SiteMapper;
import com.liuyubo.qs.db.POJO.Site;
import com.liuyubo.qs.service.SiteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_site】的数据库操作Service实现
* @createDate 2023-03-13 21:40:59
*/
@Service
public class SiteServiceImpl implements SiteService {

    final SiteMapper siteMapper;

    public SiteServiceImpl(SiteMapper siteMapper) {
        this.siteMapper = siteMapper;
    }

    @Override
    public ArrayList<HashMap> searchSiteByPage(HashMap param) {
        return siteMapper.searchSiteByPage(param);
    }

    @Override
    public ArrayList<HashMap> searchAllSite() {
        return siteMapper.searchAllSite();
    }

    @Override
    public Integer insert(Site site) {
        return siteMapper.insert(site);
    }

    @Override
    public Integer update(Site site) {
        return siteMapper.update(site);
    }

    @Override
    public Integer deleteSiteByIds(Integer[] ids) {
        return siteMapper.deleteSiteByIds(ids);
    }

    @Override
    public Long searchSiteCount(HashMap param) {
        return siteMapper.searchSiteCount(param);
    }

    @Override
    public ArrayList<String> searchTimeById(Integer id) {
        String timeSlot = siteMapper.searchTimeSlotById(id);
        String[] times = timeSlot.split(",");
        int[] intArray = Arrays.stream(times)
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
        return siteMapper.searchTimeById(intArray);
    }
}




