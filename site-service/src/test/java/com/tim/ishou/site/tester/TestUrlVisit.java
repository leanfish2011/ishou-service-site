package com.tim.ishou.site.tester;

import com.tim.ishou.site.exception.BadUrlException;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 测试网站是否可以访问
 */
public class TestUrlVisit {

  @Test
  public void testUrlVisit() {
    String[] urls = {"https://www.cnblogs.com/leanfish/", "https://tool.lu/",
        "http://www.25os.com/default.html", "https://www.shiyanlou.com/",
        "https://xclient.info/favicon.ico"};
    for (int i = 0, size = urls.length; i < size; i++) {
      String url = urls[i];

      boolean r = isVisit(url);
      if (r) {
        System.out.println(url + " yes");
      } else {
        System.out.println(url + " no");
      }

      boolean r2 = testUrlWithTimeOut(url);
      if (r2) {
        System.out.println(url + " yes");
      } else {
        System.out.println(url + " no");
      }

      boolean r3 = isConnect(url);
      if (r3) {
        System.out.println(url + " yes");
      } else {
        System.out.println(url + " no");
      }
    }
  }

  public boolean isVisit(String urlStr) {
    HttpURLConnection con;
    if (urlStr == null || urlStr.length() <= 0) {
      return false;
    }

    try {
      URL url = new URL(urlStr);
      con = (HttpURLConnection) url.openConnection();
      con.setUseCaches(false);
      con.setConnectTimeout(3000);
      int state = con.getResponseCode();
      if (state == 200) {
        return true;
      }
    } catch (Exception ex) {
      return false;
    }

    return false;
  }

  public boolean testUrlWithTimeOut(String urlString) {
    URL url;
    try {
      url = new URL(urlString);
      URLConnection co = url.openConnection();
      co.setConnectTimeout(5000);
      co.connect();
      return true;
    } catch (Exception e1) {
      url = null;
      return false;
    }
  }

  /**
   * 根据url能否获取到文档，代码中使用这个
   */
  public boolean isConnect(String url) {
    Document document;
    try {
      document = Jsoup.connect(url).get();
    } catch (Exception e) {
      return false;
    }

    return true;
  }

}
