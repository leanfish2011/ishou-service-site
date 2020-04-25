package com.tim.ishou.site.service;

import com.tim.exception.type.CommonException;
import com.tim.ishou.site.po.SitePersonal;
import com.tim.ishou.site.vo.SiteHomeAdd;
import com.tim.ishou.site.vo.SiteHomeSearchReq;
import com.tim.ishou.site.vo.SiteHomeSearchResp;
import com.tim.ishou.site.vo.SiteHomeUpdate;
import java.util.List;

public interface SiteHomeService {

  /**
   * 个人网站加入到首页网站中
   *
   * @param sitePersonal 个人网站
   */
  boolean add(SitePersonal sitePersonal);

  boolean add(SiteHomeAdd siteHomeAdd);

  boolean update(SiteHomeUpdate siteHomeUpdate);

  boolean delete(String id);

  List<SiteHomeSearchResp> search(SiteHomeSearchReq siteHomeSearchReq) throws CommonException;

  SiteHomeSearchResp select(String id);
}
