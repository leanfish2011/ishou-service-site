package com.tim.ishou.site.service.impl;

import com.tim.ishou.site.component.WebContentAnalyze;
import com.tim.ishou.site.service.WebContentCheckService;
import com.tim.ishou.site.vo.SiteCheckVO;
import com.tim.ishou.site.vo.WebContentCheckVO;
import com.tim.ishou.site.vo.WebContentVO;
import com.tim.message.MainCode;
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

    boolean titleLegal = isTextLegal(title);
    if (!titleLegal) {
      webContentCheckVO.setIsPass(false);
      return webContentCheckVO;
    }

    String keyWords = webContentVO.getKeyWords();
    boolean keywordsLegal = isTextLegal(keyWords);
    if (!keywordsLegal) {
      webContentCheckVO.setIsPass(false);
      return webContentCheckVO;
    }

    String description = webContentVO.getDescription();
    boolean descLegal = isTextLegal(description);
    if (!descLegal) {
      webContentCheckVO.setIsPass(false);
      return webContentCheckVO;
    }

    webContentCheckVO.setIsPass(true);
    webContentCheckVO.setWebContentVO(webContentVO);
    return webContentCheckVO;
  }

  @Override
  public boolean siteCheck(SiteCheckVO siteCheckVO) {
    String url = siteCheckVO.getUrl();
    if (StringUtils.isEmpty(url)) {
      return false;
    }
    WebContentCheckVO webContentCheckVO = this.check(url);
    if (!webContentCheckVO.getIsPass()) {
      return false;
    }

    String name = siteCheckVO.getName();
    String remark = siteCheckVO.getRemark();
    String tag = siteCheckVO.getTag();
    if (isTextLegal(name) && isTextLegal(remark) && isTextLegal(tag)) {
      return true;
    }

    return false;
  }

  private boolean isTextLegal(String content) {
    if (StringUtils.isNotEmpty(content)) {
      Message<Boolean> result = analyseFeignClient.text(content);
      if (result != null && result.getCode().equals(MainCode.SUCCESS)
          && !result.getData()) {
        return false;
      }
    }

    return true;
  }

}
