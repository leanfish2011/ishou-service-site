package com.tim.ishou.site.dao;

import com.tim.ishou.site.po.SitePersonal;
import com.tim.ishou.site.po.SitePersonalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SitePersonalMapper {

  int countByExample(SitePersonalExample example);

  int deleteByExample(SitePersonalExample example);

  int deleteByPrimaryKey(String id);

  int insert(SitePersonal record);

  int insertSelective(SitePersonal record);

  List<SitePersonal> selectByExample(SitePersonalExample example);

  SitePersonal selectByPrimaryKey(String id);

  int updateByExampleSelective(@Param("record") SitePersonal record,
      @Param("example") SitePersonalExample example);

  int updateByExample(@Param("record") SitePersonal record,
      @Param("example") SitePersonalExample example);

  int updateByPrimaryKeySelective(SitePersonal record);

  int updateByPrimaryKey(SitePersonal record);
}