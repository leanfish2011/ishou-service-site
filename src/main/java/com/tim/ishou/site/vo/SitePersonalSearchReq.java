package com.tim.ishou.site.vo;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author：tim
 * @date：20-3-7 下午6:00
 * @description：
 */
@Data
public class SitePersonalSearchReq {

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeStart;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeEnd;

  private String name;

  private String url;

  private String remark;

  private String tag;
}
