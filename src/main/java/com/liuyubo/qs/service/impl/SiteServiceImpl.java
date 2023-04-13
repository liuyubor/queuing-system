package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.bodytrack.BodyTrack;
import com.liuyubo.qs.db.DAO.SiteMapper;
import com.liuyubo.qs.service.SiteService;
import com.liuyubo.qs.utils.RandomV;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.valueOf;

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
    public HashMap searchSiteInfoById(Integer id) {
        return siteMapper.searchSiteInfoById(id);
    }

    @Override
    public Integer updateSiteCount(Integer id) {
        return siteMapper.updateSiteCount(id);
    }

    @SneakyThrows
    @Override
    public String searchDequeueCountById(Integer id) {
        String path = valueOf((int) ((Math.random()*10)));
        // 获取随机base64图
        String base64 = RandomV.getVideoPic(ResourceUtils.getFile("classpath:video/" + path + ".mp4"));
        // 获取人数
        String res = BodyTrack.body_num_base64(base64);
        return res;
    }

    @Override
    public ArrayList<HashMap> allSites(HashMap param) {
        return siteMapper.allSites(param);
    }

    @Override
    public Integer addSite(HashMap map) {
        return siteMapper.addSite(map);
    }

    @Override
    public Integer deleteSite(String id) {
        return siteMapper.deleteSite(id);
    }


}




