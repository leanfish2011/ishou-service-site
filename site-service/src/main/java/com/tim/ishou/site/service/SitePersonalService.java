package com.tim.ishou.site.service;

import com.tim.ishou.site.vo.SitePersonalAdd;
import com.tim.ishou.site.vo.SitePersonalSearchData;
import com.tim.ishou.site.vo.SitePersonalSearchReq;
import com.tim.ishou.site.vo.SitePersonalSearchResp;
import com.tim.ishou.site.vo.SitePersonalUpdate;

public interface SitePersonalService {

  Boolean add(SitePersonalAdd sitePersonalAdd);

  Boolean delete(String id);

  Boolean update(SitePersonalUpdate sitePersonalUpdate);

  SitePersonalSearchData search(SitePersonalSearchReq sitePersonalSearchReq);

  SitePersonalSearchResp select(String id);
}
