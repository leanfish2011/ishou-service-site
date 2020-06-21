package com.tim.ishou.site.component;

import com.tim.exception.type.ParameterException;
import com.tim.ishou.site.util.UrlUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import net.anumbrella.seaweedfs.core.FileTemplate;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：20-6-21 下午5:35
 * @description：
 */
@Component
@Slf4j
public class UrlStorageComponent {

  @Autowired
  private FileTemplate fileTemplate;

  /**
   * 将网络资源存储到seaweedfs中，返回seaweedfs存储路径
   *
   * @param url 网络资源
   * @return seaweedfs存储路径
   * @throws IOException 异常
   */
  public String storage(String url) {
    InputStream inputStream = UrlUtil.getStreamFromUrl(url);

    FileHandleStatus handleStatus;
    try {
      handleStatus = fileTemplate
          .saveFileByStream(UUID.randomUUID().toString(), inputStream);
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new ParameterException("网络资源未找到");
    }

    return handleStatus.getFileUrl();
  }

  /**
   * 将网站ico存储到seaweedfs中
   *
   * @param url 网站地址
   * @return ico在seaweedfs中的地址
   */
  public String icoStorage(String url) {
    String icoUrl = UrlUtil.getSiteIcoUrl(url);
    return storage(icoUrl);
  }

}
