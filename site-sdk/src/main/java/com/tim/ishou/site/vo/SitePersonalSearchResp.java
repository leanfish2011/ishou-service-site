package com.tim.ishou.site.vo;

import java.util.Date;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-7 下午6:00
 * @description：
 */
@Data
public class SitePersonalSearchResp {

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
