package com.tim.ishou.site.service;

import com.tim.ishou.site.po.SitePersonal;
import com.tim.ishou.site.vo.SiteHomeAdd;
import com.tim.ishou.site.vo.SiteHomeSearchData;
import com.tim.ishou.site.vo.SiteHomeSearchReq;
import com.tim.ishou.site.vo.SiteHomeSearchResp;
import com.tim.ishou.site.vo.SiteHomeUpdate;

public interface SiteHomeService {

  /**
   * 个人网站加入到首页网站中
   *
   * @param sitePersonal 个人网站
   */
  Boolean add(SitePersonal sitePersonal);

  Boolean add(SiteHomeAdd siteHomeAdd);

  Boolean update(SiteHomeUpdate siteHomeUpdate);

  Boolean delete(String id);

  SiteHomeSearchData search(SiteHomeSearchReq siteHomeSearchReq);

  SiteHomeSearchResp select(String id);
}
