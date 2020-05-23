package com.tim.ishou.site.service.impl;

import com.tim.exception.type.ParameterException;
import com.tim.ishou.site.dao.SiteHomeMapper;
import com.tim.ishou.site.po.SiteHome;
import com.tim.ishou.site.service.SiteService;
import com.tim.ishou.site.vo.SiteSearchReq;
import com.tim.ishou.site.vo.SiteSearchResp;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：20-5-23 下午5:42
 * @description：
 */
@Service
public class SiteServiceImpl implements SiteService {

  @Autowired
  private SiteHomeMapper siteHomeMapper;

  @Override
  public List<SiteSearchResp> search(SiteSearchReq siteSearchReq) throws ParameterException {
    //默认搜索主页
    String keyword = siteSearchReq.getKeyword();
    if (StringUtils.isEmpty(keyword)) {
      throw new ParameterException("关键词不能为空！");
    }

    List<SiteHome> siteHomeList = siteHomeMapper.selectByKeyword(keyword);
    List<SiteSearchResp> list = new ArrayList<>();
    for (SiteHome siteHome : siteHomeList) {
      SiteSearchResp siteSearchResp = new SiteSearchResp();
      BeanUtils.copyProperties(siteHome, siteSearchResp);

      list.add(siteSearchResp);
    }

    return list;
  }
}
