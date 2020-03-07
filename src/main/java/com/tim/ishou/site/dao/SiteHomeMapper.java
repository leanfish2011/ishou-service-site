package com.tim.ishou.site.dao;

import com.tim.ishou.site.po.SiteHome;
import com.tim.ishou.site.po.SiteHomeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SiteHomeMapper {

  int countByExample(SiteHomeExample example);

  int deleteByExample(SiteHomeExample example);

  int deleteByPrimaryKey(String id);

  int insert(SiteHome record);

  int insertSelective(SiteHome record);

  List<SiteHome> selectByExample(SiteHomeExample example);

  SiteHome selectByPrimaryKey(String id);

  int updateByExampleSelective(@Param("record") SiteHome record,
      @Param("example") SiteHomeExample example);

  int updateByExample(@Param("record") SiteHome record, @Param("example") SiteHomeExample example);

  int updateByPrimaryKeySelective(SiteHome record);

  int updateByPrimaryKey(SiteHome record);
}