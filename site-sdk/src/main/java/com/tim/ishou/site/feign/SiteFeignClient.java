package com.tim.ishou.site.feign;

import com.tim.config.FeignConfiguration;
import com.tim.ishou.site.vo.SiteHomeSearchData;
import com.tim.message.Message;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${site.server.name:ishou-service-site}", path = "${site.api.version.module:/api/ishou/v2/site/index}", configuration = FeignConfiguration.class)
public interface SiteFeignClient {

  @RequestMapping(method = RequestMethod.GET)
  Message<SiteHomeSearchData> list();

}
