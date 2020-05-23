package com.tim.ishou.site.service;

import com.tim.exception.type.CommonException;
import com.tim.ishou.site.vo.SiteSearchReq;
import com.tim.ishou.site.vo.SiteSearchResp;
import java.util.List;

public interface SiteService {

  List<SiteSearchResp> search(SiteSearchReq siteSearchReq) throws CommonException;

}
