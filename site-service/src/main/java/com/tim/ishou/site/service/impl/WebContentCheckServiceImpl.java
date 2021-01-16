package com.tim.ishou.site.service.impl;

import com.tim.ishou.site.component.WebContentAnalyze;
import com.tim.ishou.site.service.WebContentCheckService;
import com.tim.ishou.site.vo.WebContentCheckVO;
import com.tim.ishou.site.vo.WebContentVO;
import com.tim.message.Message;
import com.tim.system.sdk.feign.AnalyseFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date： 2021-01-16 下午12:16
 * @description：网页内容检查
 */
@Service
@Slf4j
public class WebContentCheckServiceImpl implements WebContentCheckService {

  @Autowired
  @Lazy
  private AnalyseFeignClient analyseFeignClient;

  @Autowired
  private WebContentAnalyze webContentAnalyze;

  @Override
  public WebContentCheckVO check(String url) {
    WebContentCheckVO webContentCheckVO = new WebContentCheckVO();
    WebContentVO webContentVO = webContentAnalyze.analyzeWebContent(url);
    String title = webContentVO.getTitle();
    if (StringUtils.isEmpty(title)) {
      webContentCheckVO.setIsPass(false);
      return webContentCheckVO;
    }

    Message<Boolean> titleResult = analyseFeignClient.text(title);
    if (!titleResult.getData()) {
      webContentCheckVO.setIsPass(false);
      return webContentCheckVO;
    }

    String keyWords = webContentVO.getKeyWords();
    if (isIllegal(webContentCheckVO, keyWords)) {
      return webContentCheckVO;
    }

    String description = webContentVO.getDescription();
    if (isIllegal(webContentCheckVO, description)) {
      return webContentCheckVO;
    }

    webContentCheckVO.setIsPass(true);
    webContentCheckVO.setWebContentVO(webContentVO);
    return webContentCheckVO;
  }

  private boolean isIllegal(WebContentCheckVO webContentCheckVO, String content) {
    if (StringUtils.isNotEmpty(content)) {
      Message<Boolean> keywordsResult = analyseFeignClient.text(content);
      if (!keywordsResult.getData()) {
        webContentCheckVO.setIsPass(false);
        return true;
      }
    }
    return false;
  }

}
