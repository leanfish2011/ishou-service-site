package com.tim.ishou.site.component;

import com.tim.ishou.site.exception.BadUrlException;
import com.tim.ishou.site.vo.WebContentVO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date： 2021-01-16 下午12:07
 * @description：网页内容解析
 */
@Component
@Slf4j
public class WebContentAnalyze {

  public WebContentVO analyzeWebContent(String url) {
    WebContentVO webContentVO = new WebContentVO();
    Document document;
    try {
      document = Jsoup.connect(url).get();
    } catch (Exception e) {
      log.warn(e.toString());
      throw new BadUrlException("资源不存在或者网络异常！");
    }

    //获取标题信息
    webContentVO.setTitle(document.title());

    //获取描述信息
    Elements descElements = document.select("meta[name=description]");
    if (descElements != null && descElements.size() > 0) {
      String description = descElements.first().attr("content");
      webContentVO.setDescription(description);
    }

    //获取关键词信息
    Elements keywsElements = document.select("meta[name=keywords]");
    if (keywsElements != null && keywsElements.size() > 0) {
      String keywords = keywsElements.first().attr("content");
      webContentVO.setKeyWords(keywords);
    }

    return webContentVO;
  }
}
