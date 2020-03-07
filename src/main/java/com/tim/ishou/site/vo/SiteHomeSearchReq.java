package com.tim.ishou.site.vo;

import java.util.Date;
import lombok.Data;

@Data
public class SiteHomeSearchReq {

  private Date createTimeStart;

  private Date createTimeEnd;

  private String creatorId;

  private String name;

  private String url;

  private String remark;

  private String tag;

}