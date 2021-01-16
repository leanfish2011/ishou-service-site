package com.tim.ishou.site.service;

import com.tim.ishou.site.vo.SiteCheckVO;
import com.tim.ishou.site.vo.WebContentCheckVO;

/**
 * @author：tim
 * @date： 2021-01-16 下午3:17
 * @description：
 */
public interface WebContentCheckService {

  WebContentCheckVO check(String url);

  boolean siteCheck(SiteCheckVO siteCheckVO);
}
