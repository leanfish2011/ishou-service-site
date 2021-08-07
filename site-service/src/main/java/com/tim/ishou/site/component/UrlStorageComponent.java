package com.tim.ishou.site.component;

import com.tim.exception.type.ParameterException;
import com.tim.ishou.site.util.UrlUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import net.anumbrella.seaweedfs.core.FileTemplate;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import org.apache.commons.lang.StringUtils;
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
      log.error("保存网络资源异常，网络资源未找到。{}", e);
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
    String icoUrlStorage;
    try {
      String icoUrl = UrlUtil.getSiteIcoUrl(url);
      icoUrlStorage = storage(icoUrl);
    } catch (Exception e) {
      log.warn("存储网站图标失败：{}", e);
      return "";
    }

    return icoUrlStorage;
  }

  /**
   * 根据资源id删除资源
   *
   * @param fileId 资源在seaweedfs中的id
   */
  public void deleteStorage(String fileId) {
    try {
      fileTemplate.deleteFile(fileId);
    } catch (IOException e) {
      log.error("删除seaweedfs资源异常，未找到资源。{}", e);
      throw new ParameterException("网络资源未找到");
    }
  }

  /**
   * 删除seaweedfs中的文件
   *
   * @param fileUrl ico路径
   */
  public void deleteIco(String fileUrl) {
    if (StringUtils.isEmpty(fileUrl)) {
      return;
    }

    String fileId = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    deleteStorage(fileId);
  }

}
