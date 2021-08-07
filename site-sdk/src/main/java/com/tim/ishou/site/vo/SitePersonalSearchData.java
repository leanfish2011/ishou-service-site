package com.tim.ishou.site.vo;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-7-1 下午11:08
 * @description：
 */
@Data
public class SitePersonalSearchData {

  private Integer allTotal;

  private Integer currentTotal;

  private List<SitePersonalSearchResp> list;
}
