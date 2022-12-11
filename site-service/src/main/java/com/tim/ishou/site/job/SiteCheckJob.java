package com.tim.ishou.site.job;

import com.alibaba.fastjson.JSON;
import com.tim.ishou.site.config.ThreadPoolUtil;
import com.tim.ishou.site.service.SiteHomeService;
import com.tim.ishou.site.service.WebContentCheckService;
import com.tim.ishou.site.vo.SiteHomeSearchData;
import com.tim.ishou.site.vo.SiteHomeSearchResp;
import com.tim.ishou.site.vo.WebContentCheckVO;
import java.util.List;
import java.util.concurrent.ExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：2022-12-11 10:50 AM
 * @description：网站内容检查：1、网站内容是否合法
 */
@Component
@Slf4j
public class SiteCheckJob {

  @Autowired
  private SiteHomeService siteHomeService;

  @Autowired
  private WebContentCheckService webContentCheckService;

  @Value("${home.site.check.switch:true}")
  private boolean checkSwitch;

  @Scheduled(cron = "${home.site.check.cron:0 0 00 * * ?}")
  public void homeSiteCheck() {
    if (!checkSwitch) {
      log.warn("检查主页网站定时任务未开启！");
      return;
    }

    log.info("检查主页网站定时任务启动");

    SiteHomeSearchData siteHomeSearchData = siteHomeService.search(null);
    if (siteHomeSearchData.getAllTotal() == 0) {
      return;
    }

    List<SiteHomeSearchResp> siteHomeList = siteHomeSearchData.getList();
    ExecutorService executor = ThreadPoolUtil.build();
    for (SiteHomeSearchResp siteHomeSearchResp : siteHomeList) {
      executor.submit(() -> handleSite(siteHomeSearchResp));
    }

    executor.shutdown();
  }

  private void handleSite(SiteHomeSearchResp siteHomeSearchResp) {
    WebContentCheckVO webContentCheckVO = webContentCheckService.check(siteHomeSearchResp.getUrl());
    if (!webContentCheckVO.getIsPass()) {
      siteHomeService.delete(siteHomeSearchResp.getId());
      log.warn("网站存在不合法内容，已经删除！{}", JSON.toJSON(siteHomeSearchResp));
    } else {
      log.info("网站合法：{}", siteHomeSearchResp.getName());
    }
  }

}
