package com.tim.ishou.site.controller;

import com.tim.exception.type.CommonException;
import com.tim.ishou.site.service.SitePersonalService;
import com.tim.ishou.site.vo.SitePersonalAdd;
import com.tim.ishou.site.vo.SitePersonalSearchData;
import com.tim.ishou.site.vo.SitePersonalSearchReq;
import com.tim.ishou.site.vo.SitePersonalSearchResp;
import com.tim.ishou.site.vo.SitePersonalUpdate;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
@Api(description = "个人网站管理")
@RestController
@RequestMapping("${api.version.module}/personal")
public class SitePersonalController {

  @Autowired
  private SitePersonalService sitePersonalService;

  @ApiOperation(value = "新增网站")
  @RequestMapping(method = RequestMethod.POST)
  public Message add(@RequestBody SitePersonalAdd sitePersonalAdd) throws CommonException {
    sitePersonalService.add(sitePersonalAdd);
    return Message.success();
  }

  @ApiOperation(value = "删除网站")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    sitePersonalService.delete(id);
    return Message.success();
  }

  @ApiOperation(value = "修改网站")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody SitePersonalUpdate sitePersonalUpdate) {
    sitePersonalService.update(sitePersonalUpdate);
    return Message.success();
  }

  @ApiOperation(value = "获取网站信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Message<SitePersonalSearchResp> select(@PathVariable String id) {
    return Message.success(sitePersonalService.select(id));
  }

  @ApiOperation(value = "查询网站")
  @RequestMapping(method = RequestMethod.GET)
  public Message<SitePersonalSearchData> search(SitePersonalSearchReq sitePersonalSearchReq) {
    return Message.success(sitePersonalService.search(sitePersonalSearchReq));
  }
}
