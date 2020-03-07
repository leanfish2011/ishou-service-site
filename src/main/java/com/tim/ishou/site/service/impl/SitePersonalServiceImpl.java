package com.tim.ishou.site.service.impl;

import com.tim.ishou.site.dao.SitePersonalMapper;
import com.tim.ishou.site.exception.ParameterException;
import com.tim.ishou.site.po.SitePersonal;
import com.tim.ishou.site.po.SitePersonalExample;
import com.tim.ishou.site.po.SitePersonalExample.Criteria;
import com.tim.ishou.site.service.SiteHomeService;
import com.tim.ishou.site.service.SitePersonalService;
import com.tim.ishou.site.vo.SitePersonalAdd;
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

  @Override
  public boolean add(SitePersonalAdd sitePersonalAdd) {
    SitePersonal sitePersonal = new SitePersonal();
    BeanUtils.copyProperties(sitePersonalAdd, sitePersonal);
    sitePersonal.setId(UUID.randomUUID().toString());
    //TODO
    sitePersonal.setCreatorId("1");

    if (sitePersonalAdd.getIsPost()) {
      siteHomeService.add(sitePersonal);
    }

    return sitePersonalMapper.insertSelective(sitePersonal) > 0 ? true : false;
  }

  @Override
  public boolean delete(String id) {
    return sitePersonalMapper.deleteByPrimaryKey(id) > 0 ? true : false;
  }

  @Override
  public boolean update(SitePersonalUpdate sitePersonalUpdate) {
    SitePersonal sitePersonal = new SitePersonal();
    BeanUtils.copyProperties(sitePersonalUpdate, sitePersonal);
    //TODO
    sitePersonal.setModifierId("1");
    if (sitePersonalUpdate.getIsPost()) {
      siteHomeService.add(sitePersonal);
    }

    return sitePersonalMapper.updateByPrimaryKeySelective(sitePersonal) > 0 ? true : false;
  }

  @Override
  public List<SitePersonalSearchResp> search(SitePersonalSearchReq sitePersonalSearchReq) {
    SitePersonalExample sitePersonalExample = new SitePersonalExample();
    Criteria criteria = sitePersonalExample.createCriteria();

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
      throw new ParameterException("开始时间不能大于结束时间");
    }

    sitePersonalExample.setOrderByClause(" create_time asc,sort_num asc");

    List<SitePersonal> sitePersonalList = sitePersonalMapper.selectByExample(sitePersonalExample);
    List<SitePersonalSearchResp> list = new ArrayList<>();
    for (SitePersonal sitePersonal : sitePersonalList) {
      SitePersonalSearchResp sitePersonalSearchResp = new SitePersonalSearchResp();
      BeanUtils.copyProperties(sitePersonal, sitePersonalSearchResp);

      list.add(sitePersonalSearchResp);
    }

    return list;
  }

  @Override
  public SitePersonalSearchResp select(String id) {
    SitePersonal sitePersonal = sitePersonalMapper.selectByPrimaryKey(id);
    SitePersonalSearchResp sitePersonalSearchResp = new SitePersonalSearchResp();
    BeanUtils.copyProperties(sitePersonal, sitePersonalSearchResp);

    return sitePersonalSearchResp;
  }

}
