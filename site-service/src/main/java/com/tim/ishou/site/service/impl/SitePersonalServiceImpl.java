package com.tim.ishou.site.service.impl;

import com.tim.auth.sdk.component.AccountInfo;
import com.tim.auth.sdk.vo.TokenModel;
import com.tim.exception.type.ParameterException;
import com.tim.ishou.site.component.UrlStorageComponent;
import com.tim.ishou.site.dao.SitePersonalMapper;
import com.tim.ishou.site.exception.IllegalException;
import com.tim.ishou.site.po.SitePersonal;
import com.tim.ishou.site.po.SitePersonalExample;
import com.tim.ishou.site.po.SitePersonalExample.Criteria;
import com.tim.ishou.site.service.SiteHomeService;
import com.tim.ishou.site.service.SitePersonalService;
import com.tim.ishou.site.service.WebContentCheckService;
import com.tim.ishou.site.util.UrlUtil;
import com.tim.ishou.site.vo.SiteCheckVO;
import com.tim.ishou.site.vo.SitePersonalAdd;
import com.tim.ishou.site.vo.SitePersonalSearchData;
import com.tim.ishou.site.vo.SitePersonalSearchReq;
import com.tim.ishou.site.vo.SitePersonalSearchResp;
import com.tim.ishou.site.vo.SitePersonalUpdate;
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
 * @date：20-3-7 下午5:39
 * @description：
 */
@Service
@Slf4j
public class SitePersonalServiceImpl implements SitePersonalService {

  @Autowired
  private SitePersonalMapper sitePersonalMapper;

  @Autowired
  private SiteHomeService siteHomeService;

  @Autowired
  private AccountInfo accountInfo;

  @Autowired
  private WebContentCheckService webContentCheckService;

  @Override
  public Boolean add(SitePersonalAdd sitePersonalAdd) {
    SiteCheckVO siteCheckVO = new SiteCheckVO();
    BeanUtils.copyProperties(sitePersonalAdd, siteCheckVO);
    if (!webContentCheckService.siteCheck(siteCheckVO)) {
      throw new IllegalException();
    }

    SitePersonal sitePersonal = new SitePersonal();
    BeanUtils.copyProperties(sitePersonalAdd, sitePersonal);
    sitePersonal.setId(UUID.randomUUID().toString());
    TokenModel tokenModel = accountInfo.getUserInfo();
    sitePersonal.setCreatorId(tokenModel.getLoginResp().getUserId());

    String fileUrl = UrlUtil.getSiteIcoUrl(sitePersonalAdd.getUrl());
    sitePersonal.setIconUrl(fileUrl);

    if (sitePersonalAdd.getIsPost()) {
      siteHomeService.add(sitePersonal);
    }

    return sitePersonalMapper.insertSelective(sitePersonal) > 0 ? true : false;
  }

  @Override
  public Boolean delete(String id) {
    //SitePersonalSearchResp sitePersonalSearchResp = select(id);
    //urlStorageComponent.deleteIco(sitePersonalSearchResp.getIconUrl());

    return sitePersonalMapper.deleteByPrimaryKey(id) > 0 ? true : false;
  }

  @Override
  public Boolean update(SitePersonalUpdate sitePersonalUpdate) {
    SitePersonal sitePersonal = new SitePersonal();
    BeanUtils.copyProperties(sitePersonalUpdate, sitePersonal);

    TokenModel tokenModel = accountInfo.getUserInfo();
    sitePersonal.setModifierId(tokenModel.getLoginResp().getUserId());
    if (sitePersonalUpdate.getIsPost()) {
      sitePersonal.setCreatorId(tokenModel.getLoginResp().getUserId());
      siteHomeService.add(sitePersonal);
    }

    return sitePersonalMapper.updateByPrimaryKeySelective(sitePersonal) > 0 ? true : false;
  }

  @Override
  public SitePersonalSearchData search(SitePersonalSearchReq sitePersonalSearchReq) {
    SitePersonalExample sitePersonalExample = new SitePersonalExample();
    Criteria criteria = sitePersonalExample.createCriteria();

    if (sitePersonalSearchReq != null) {
      if (!StringUtils.isEmpty(sitePersonalSearchReq.getName())) {
        criteria.andNameLike("%" + sitePersonalSearchReq.getName() + "%");
      }

      if (!StringUtils.isEmpty(sitePersonalSearchReq.getUrl())) {
        criteria.andUrlLike("%" + sitePersonalSearchReq.getUrl() + "%");
      }

      if (!StringUtils.isEmpty(sitePersonalSearchReq.getRemark())) {
        criteria.andRemarkLike("%" + sitePersonalSearchReq.getRemark() + "%");
      }

      if (!StringUtils.isEmpty(sitePersonalSearchReq.getTag())) {
        criteria.andTagLike("%" + sitePersonalSearchReq.getTag() + "%");
      }

      Date start = sitePersonalSearchReq.getCreateTimeStart();
      Date end = sitePersonalSearchReq.getCreateTimeEnd();
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

      Integer pageNo = sitePersonalSearchReq.getPageNo();
      Integer pageSize = sitePersonalSearchReq.getPageSize();
      if (pageNo != null && pageSize != null) {
        sitePersonalExample.setLimitRange((pageNo - 1) * pageSize + "," + pageSize);
      }
    }

    TokenModel tokenModel = accountInfo.getUserInfo();
    criteria.andCreatorIdEqualTo(tokenModel.getLoginResp().getUserId());

    sitePersonalExample.setOrderByClause(" create_time desc,sort_num asc");

    List<SitePersonal> sitePersonalList = sitePersonalMapper.selectByExample(sitePersonalExample);
    List<SitePersonalSearchResp> list = new ArrayList<>();
    for (SitePersonal sitePersonal : sitePersonalList) {
      SitePersonalSearchResp sitePersonalSearchResp = new SitePersonalSearchResp();
      BeanUtils.copyProperties(sitePersonal, sitePersonalSearchResp);

      list.add(sitePersonalSearchResp);
    }

    int allTotal = sitePersonalMapper.countByExample(sitePersonalExample);

    SitePersonalSearchData sitePersonalSearchData = new SitePersonalSearchData();
    sitePersonalSearchData.setAllTotal(allTotal);
    sitePersonalSearchData.setCurrentTotal(list.size());
    sitePersonalSearchData.setList(list);

    return sitePersonalSearchData;
  }

  @Override
  public SitePersonalSearchResp select(String id) {
    SitePersonal sitePersonal = sitePersonalMapper.selectByPrimaryKey(id);
    SitePersonalSearchResp sitePersonalSearchResp = new SitePersonalSearchResp();
    BeanUtils.copyProperties(sitePersonal, sitePersonalSearchResp);

    return sitePersonalSearchResp;
  }

}
