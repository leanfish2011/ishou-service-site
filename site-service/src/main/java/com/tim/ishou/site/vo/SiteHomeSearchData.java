package com.tim.ishou.site.vo;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-6-30 下午11:26
 * @description：网站查询返回结果
 */
@Data
public class SiteHomeSearchData {

  private Integer allTotal;

  private Integer currentTotal;

  private List<SiteHomeSearchResp> list;
}
