package com.tim.ishou.site.service;

import com.tim.exception.type.CommonException;
import com.tim.ishou.site.vo.SitePersonalAdd;
import com.tim.ishou.site.vo.SitePersonalSearchReq;
import com.tim.ishou.site.vo.SitePersonalSearchResp;
import com.tim.ishou.site.vo.SitePersonalUpdate;
import java.util.List;

public interface SitePersonalService {

  boolean add(SitePersonalAdd sitePersonalAdd) throws CommonException;

  boolean delete(String id);

  boolean update(SitePersonalUpdate sitePersonalUpdate) throws CommonException;

  List<SitePersonalSearchResp> search(SitePersonalSearchReq sitePersonalSearchReq)
      throws CommonException;

  SitePersonalSearchResp select(String id);
}
