package com.tim.ishou.site.controller;

import com.tim.ishou.site.service.SiteHomeService;
import com.tim.ishou.site.service.SiteService;
import com.tim.ishou.site.vo.SiteHomeSearchData;
import com.tim.ishou.site.vo.SiteSearchReq;
import com.tim.ishou.site.vo.SiteSearchResp;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：tim
 * @date：20-3-7 下午9:37
 * @description：
 */
@Api(description = "首页")
@RestController
@RequestMapping("${api.version.module}/index")
public class IndexController {

  @Autowired
  private SiteHomeService siteHomeService;

  @Autowired
  private SiteService siteService;

  @ApiOperation(value = "列出网站")
  @RequestMapping(method = RequestMethod.GET)
  public Message<SiteHomeSearchData> list() {
    return Message.success(siteHomeService.search(null));
  }

  @ApiOperation(value = "关键词检索网站")
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public Message<List<SiteSearchResp>> search(SiteSearchReq siteSearchReq) {
    return Message.success(siteService.search(siteSearchReq));
  }

}
