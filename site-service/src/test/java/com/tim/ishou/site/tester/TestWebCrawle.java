package com.tim.ishou.site.tester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * @author：tim
 * @date： 2021-01-16 上午11:27
 * @description：
 */
public class TestWebCrawle {

  //@Test
  public void getWeb() throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();//创建httpClient
    HttpGet httpGet = new HttpGet("http://www.cnblogs.com/");//创建httpget实例

    CloseableHttpResponse response = httpClient.execute(httpGet);//执行get请求
    HttpEntity entity = response.getEntity();//获取返回实体
    String content = EntityUtils.toString(entity, "utf-8");//网页内容
    response.close();//关闭流和释放系统资源

    Document doc = Jsoup.parse(content);//解析网页得到文档对象
    Elements titlesEle = doc.getElementsByTag("title");//获取tag是title的所有dom文档
    Element title = titlesEle.get(0);//获取第一个元素
    String titleText = title.text(); //.html是返回html
    System.out.println("网页标题：" + titleText);
  }

  public void getWeb2(String url) throws IOException {
    Document document = Jsoup.connect(url).get();

    String title = document.title();
    System.out.println("Title:" + title);

    //获取描述信息
    Elements descElements = document.select("meta[name=description]");
    if (descElements != null && descElements.size() > 0) {
      String description = descElements.first().attr("content");
      System.out.println("Meta description : " + description);
    }

    //获取关键词信息
    Elements keyElements = document.select("meta[name=keywords]");
    if (keyElements != null && keyElements.size() > 0) {
      String keywords = keyElements.first().attr("content");
      System.out.println("Meta keyword : " + keywords);
    }

  }

  //@Test
  public void testUrls() throws IOException {
    List<String> urls = new ArrayList();
    urls.add("https://www.cnblogs.com11");
    urls.add("http://106.53.116.69/");
    urls.add("https://www.oschina.net/");
    urls.add("https://www.cnblogs.com/JavaArchitect/");
    urls.add("https://www.jianshu.com/p/63449d21b4b9");
    urls.add("https://www.cnblogs.com/Createsequence/p/13216632.html");

    for (String url : urls) {
      System.out.println(url);
      getWeb2(url);
    }

  }

}
