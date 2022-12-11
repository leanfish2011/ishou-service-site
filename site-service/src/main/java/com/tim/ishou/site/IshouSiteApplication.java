package com.tim.ishou.site;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author：tim
 * @date：20-3-7 下午5:26
 * @description：
 */
@ComponentScan({"com.tim"})
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.tim.ishou.site.dao")
@EnableFeignClients("com.tim")
@EnableScheduling
public class IshouSiteApplication {

  public static void main(String[] args) {
    SpringApplication.run(IshouSiteApplication.class, args);
  }

}
