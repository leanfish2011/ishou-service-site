package com.tim.ishou.site.po;

import java.util.Date;
import lombok.Data;

@Data
public class SitePersonal {

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String name;

  private String url;

  private String remark;

  private String tag;

  private Integer sortNum;

  private String iconUrl;
}
