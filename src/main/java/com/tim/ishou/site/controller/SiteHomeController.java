package com.tim.ishou.site.controller;

import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

}
