package com.tim.ishou.site.controller;

import com.tim.ishou.site.service.SiteHomeService;
import com.tim.ishou.site.vo.SiteHomeAdd;
import com.tim.ishou.site.vo.SiteHomeSearchData;
import com.tim.ishou.site.vo.SiteHomeSearchReq;
import com.tim.ishou.site.vo.SiteHomeSearchResp;
import com.tim.ishou.site.vo.SiteHomeUpdate;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：tim
 * @date：20-3-7 下午5:29
 * @description：
 */
@Api(description = "首页网站管理")
@RestController
@RequestMapping("${api.version.module}/home")
public class SiteHomeController {

  @Autowired
  private SiteHomeService siteHomeService;

  @ApiOperation(value = "新增网站")
  @RequestMapping(method = RequestMethod.POST)
  public Message add(@RequestBody SiteHomeAdd siteHomeAdd) {
    siteHomeService.add(siteHomeAdd);
    return Message.success();
  }

  @ApiOperation(value = "删除网站")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    siteHomeService.delete(id);
    return Message.success();
  }

  @ApiOperation(value = "修改网站")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody SiteHomeUpdate siteHomeUpdate) {
    siteHomeService.update(siteHomeUpdate);
    return Message.success();
  }

  @ApiOperation(value = "获取网站信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Message<SiteHomeSearchResp> select(@PathVariable String id) {
    return Message.success(siteHomeService.select(id));
  }

  @ApiOperation(value = "查询网站")
  @RequestMapping(method = RequestMethod.GET)
  public Message<SiteHomeSearchData> search(@Valid SiteHomeSearchReq siteHomeSearchReq) {
    return Message.success(siteHomeService.search(siteHomeSearchReq));
  }
}
