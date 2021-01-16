package com.tim.ishou.site.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author：tim
 * @date： 2021-01-16 下午3:58
 * @description：
 */
@Data
public class WebContentCheckReq {

  @NotBlank
  private String url;
}
