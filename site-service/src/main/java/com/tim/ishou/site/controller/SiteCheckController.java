package com.tim.ishou.site.controller;

import com.tim.ishou.site.service.WebContentCheckService;
import com.tim.ishou.site.vo.WebContentCheckVO;
import com.tim.ishou.site.vo.WebContentCheckReq;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：tim
 * @date： 2021-01-16 下午3:18
 * @description：
 */
@Api(description = "网站管理")
@RestController
@RequestMapping("${api.version.module}/check")
public class SiteCheckController {

  @Autowired
  private WebContentCheckService webContentCheckService;

  @ApiOperation(value = "检查网站")
  @PostMapping
  public Message<WebContentCheckVO> check(@RequestBody WebContentCheckReq webContentCheckReq) {
    return Message.success(webContentCheckService.check(webContentCheckReq.getUrl()));
  }
}
