package com.tim.ishou.site.vo;

import java.util.Date;
import javax.validation.constraints.Min;
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

  @Min(value = 1, message = "分页起始页不能小于1")
  private Integer pageNo;

  @Min(value = 1, message = "每页条数不能小于1")
  private Integer pageSize;

}
