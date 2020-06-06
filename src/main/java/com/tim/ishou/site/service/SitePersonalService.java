package com.tim.ishou.site.service;

import com.tim.ishou.site.vo.SitePersonalAdd;
import com.tim.ishou.site.vo.SitePersonalSearchReq;
import com.tim.ishou.site.vo.SitePersonalSearchResp;
import com.tim.ishou.site.vo.SitePersonalUpdate;
import java.util.List;

public interface SitePersonalService {

  Boolean add(SitePersonalAdd sitePersonalAdd);

  Boolean delete(String id);

  Boolean update(SitePersonalUpdate sitePersonalUpdate);

  List<SitePersonalSearchResp> search(SitePersonalSearchReq sitePersonalSearchReq);

  SitePersonalSearchResp select(String id);
}
