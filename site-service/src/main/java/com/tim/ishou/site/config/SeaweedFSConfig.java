package com.tim.ishou.site.config;

import java.io.IOException;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FileTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：tim
 * @date：20-6-21 上午10:47
 * @description：
 */
// @Configuration
public class SeaweedFSConfig {

  @Value("${seaweedfs.host:127.0.0.1}")
  private String host;

  @Value("${seaweedfs.port:9333}")
  private int port;

  @Bean
  public FileTemplate fileTemplate() {
    FileSource fileSource = new FileSource();
    fileSource.setHost(host);
    fileSource.setPort(port);

    try {
      // 启动服务
      fileSource.startup();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new FileTemplate(fileSource.getConnection());
  }
}
