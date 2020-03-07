package com.tim.ishou.site.vo;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SiteHomeSearchReq {

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeStart;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeEnd;

  private String creatorId;

  private String name;

  private String url;

  private String remark;

  private String tag;

}