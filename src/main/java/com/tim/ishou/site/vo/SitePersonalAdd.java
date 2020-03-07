package com.tim.ishou.site.vo;

import lombok.Data;

/**
 * @author：tim
 * @date：20-3-7 下午5:35
 * @description：
 */
@Data
public class SitePersonalAdd {

  private String name;

  private String url;

  private String remark;

  private String tag;

  /**
   * 发布到首页
   */
  private Boolean isPost;
}
