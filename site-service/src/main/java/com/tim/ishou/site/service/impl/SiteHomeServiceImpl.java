package com.tim.ishou.site.service.impl;

import com.tim.auth.sdk.constant.AuthConstant;
import com.tim.exception.type.ParameterException;
import com.tim.ishou.site.component.UrlStorageComponent;
import com.tim.ishou.site.dao.SiteHomeMapper;
import com.tim.ishou.site.exception.IllegalException;
import com.tim.ishou.site.po.SiteHome;
import com.tim.ishou.site.po.SiteHomeExample;
import com.tim.ishou.site.po.SiteHomeExample.Criteria;
import com.tim.ishou.site.po.SitePersonal;
import com.tim.ishou.site.service.SiteHomeService;
import com.tim.ishou.site.service.WebContentCheckService;
import com.tim.ishou.site.util.UrlUtil;
import com.tim.ishou.site.vo.SiteCheckVO;
import com.tim.ishou.site.vo.SiteHomeAdd;
import com.tim.ishou.site.vo.SiteHomeSearchData;
import com.tim.ishou.site.vo.SiteHomeSearchReq;
import com.tim.ishou.site.vo.SiteHomeSearchResp;
import com.tim.ishou.site.vo.SiteHomeUpdate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：20-3-7 下午8:56
 * @description：
 */
@Service
@Slf4j
public class SiteHomeServiceImpl implements SiteHomeService {

  @Autowired
  private SiteHomeMapper siteHomeMapper;

  @Autowired
  private WebContentCheckService webContentCheckService;

  @Override
  public Boolean add(SitePersonal sitePersonal) {
    SiteCheckVO siteCheckVO = new SiteCheckVO();
    BeanUtils.copyProperties(sitePersonal, siteCheckVO);
    if (!webContentCheckService.siteCheck(siteCheckVO)) {
      throw new IllegalException();
    }

    SiteHome siteHome = new SiteHome();
    BeanUtils.copyProperties(sitePersonal, siteHome);
    siteHome.setId(UUID.randomUUID().toString());

    return siteHomeMapper.insertSelective(siteHome) > 0 ? true : false;
  }

  @Override
  public Boolean add(SiteHomeAdd siteHomeAdd) {
    SiteCheckVO siteCheckVO = new SiteCheckVO();
    BeanUtils.copyProperties(siteHomeAdd, siteCheckVO);
    if (!webContentCheckService.siteCheck(siteCheckVO)) {
      throw new IllegalException();
    }

    SiteHome siteHome = new SiteHome();
    BeanUtils.copyProperties(siteHomeAdd, siteHome);
    siteHome.setId(UUID.randomUUID().toString());
    siteHome.setCreatorId(AuthConstant.USER_ADMIN_ID);

//    //网站图标存储到seaweedfs
//    String fileUrl = urlStorageComponent.icoStorage(siteHomeAdd.getUrl());
//    siteHome.setIconUrl(fileUrl);

    String fileUrl = UrlUtil.getSiteIcoUrl(siteHomeAdd.getUrl());
    siteHome.setIconUrl(fileUrl);

    return siteHomeMapper.insertSelective(siteHome) > 0 ? true : false;
  }

  @Override
  public Boolean update(SiteHomeUpdate siteHomeUpdate) {
    SiteHome siteHome = new SiteHome();
    BeanUtils.copyProperties(siteHomeUpdate, siteHome);

    siteHome.setModifierId(AuthConstant.USER_ADMIN_ID);

    return siteHomeMapper.updateByPrimaryKeySelective(siteHome) > 0 ? true : false;
  }

  @Override
  public Boolean delete(String id) {
    // SiteHomeSearchResp siteHomeSearchResp = select(id);
    // urlStorageComponent.deleteIco(siteHomeSearchResp.getIconUrl());

    return siteHomeMapper.deleteByPrimaryKey(id) > 0 ? true : false;
  }

  @Override
  public SiteHomeSearchData search(SiteHomeSearchReq siteHomeSearchReq) {
    SiteHomeExample siteHomeExample = new SiteHomeExample();
    Criteria criteria = siteHomeExample.createCriteria();

    if (siteHomeSearchReq != null) {
      if (!StringUtils.isEmpty(siteHomeSearchReq.getName())) {
        criteria.andNameLike("%" + siteHomeSearchReq.getName() + "%");
      }

      if (!StringUtils.isEmpty(siteHomeSearchReq.getUrl())) {
        criteria.andUrlLike("%" + siteHomeSearchReq.getUrl() + "%");
      }

      if (!StringUtils.isEmpty(siteHomeSearchReq.getRemark())) {
        criteria.andRemarkLike("%" + siteHomeSearchReq.getRemark() + "%");
      }

      if (!StringUtils.isEmpty(siteHomeSearchReq.getTag())) {
        criteria.andTagLike("%" + siteHomeSearchReq.getTag() + "%");
      }

      Date start = siteHomeSearchReq.getCreateTimeStart();
      Date end = siteHomeSearchReq.getCreateTimeEnd();
      if (start != null && end == null) {
        criteria.andCreateTimeGreaterThanOrEqualTo(start);
      }

      if (start == null && end != null) {
        criteria.andCreateTimeLessThanOrEqualTo(end);
      }

      if (start != null && end != null) {
        criteria.andCreateTimeBetween(start, end);
      }

      if (start != null && end != null && start.after(end)) {
        log.warn("开始时间不能大于结束时间。开始时间：{},结束时间：{}", start, end);
        throw new ParameterException("开始时间不能大于结束时间");
      }

      Integer pageNo = siteHomeSearchReq.getPageNo();
      Integer pageSize = siteHomeSearchReq.getPageSize();
      if (pageNo != null && pageSize != null) {
        siteHomeExample.setLimitRange((pageNo - 1) * pageSize + "," + pageSize);
      }
    }

    siteHomeExample.setOrderByClause(" create_time desc,sort_num asc");

    List<SiteHome> siteHomeList = siteHomeMapper.selectByExample(siteHomeExample);
    List<SiteHomeSearchResp> list = new ArrayList<>();
    for (SiteHome siteHome : siteHomeList) {
      SiteHomeSearchResp siteHomeSearchResp = new SiteHomeSearchResp();
      BeanUtils.copyProperties(siteHome, siteHomeSearchResp);

      list.add(siteHomeSearchResp);
    }

    int allTotal = siteHomeMapper.countByExample(siteHomeExample);

    SiteHomeSearchData siteHomeSearchData = new SiteHomeSearchData();
    siteHomeSearchData.setAllTotal(allTotal);
    siteHomeSearchData.setCurrentTotal(list.size());
    siteHomeSearchData.setList(list);

    return siteHomeSearchData;
  }

  @Override
  public SiteHomeSearchResp select(String id) {
    SiteHome siteHome = siteHomeMapper.selectByPrimaryKey(id);
    SiteHomeSearchResp siteHomeSearchResp = new SiteHomeSearchResp();
    BeanUtils.copyProperties(siteHome, siteHomeSearchResp);

    return siteHomeSearchResp;
  }
}
