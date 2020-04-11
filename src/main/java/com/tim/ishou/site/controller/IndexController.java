package com.tim.ishou.site.controller;

import com.tim.exception.type.CommonException;
import com.tim.ishou.site.service.SiteHomeService;
import com.tim.ishou.site.vo.SiteHomeSearchReq;
import com.tim.ishou.site.vo.SiteHomeSearchResp;
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

  @ApiOperation(value = "查询网站")
  @RequestMapping(method = RequestMethod.GET)
  public Message<List<SiteHomeSearchResp>> search(SiteHomeSearchReq siteHomeSearchReq)
      throws CommonException {
    return Message.success(siteHomeService.search(siteHomeSearchReq));
  }
}
