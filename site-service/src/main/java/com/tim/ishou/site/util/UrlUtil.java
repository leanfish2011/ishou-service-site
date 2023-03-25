package com.tim.ishou.site.util;

import com.tim.exception.type.ParameterException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：tim
 * @date：20-6-21 下午12:38
 * @description： url工具
 */
public class UrlUtil {

  /**
   * 读取url，得到流
   *
   * @param url 网络资源路径
   */
  public static InputStream getStreamFromUrl(String url) {
    URL objUrl;
    try {
      objUrl = new URL(url);
      HttpURLConnection httpURLConnection = (HttpURLConnection) objUrl.openConnection();
      httpURLConnection.setRequestMethod("GET");
      httpURLConnection.setConnectTimeout(5000);
      httpURLConnection.setReadTimeout(5000);

      if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream inputStream = httpURLConnection.getInputStream();
        return inputStream;
      }
    } catch (Exception e) {
      throw new ParameterException("读取网络资源异常！" + e);
    }

    return null;
  }

  /**
   * 获取网站ico路径
   *
   * @param url 网站地址
   * @return 网站ico路径
   */
  public static String getSiteIcoUrl(String url) {
    Pattern p = Pattern.compile(
        "((https?|http|ftp|file):\\/\\/)?[-A-Za-z0-9+&@#\\/%?=~_|!:,.;]+\\.[-A-Za-z+]+\\/");

    Matcher m = p.matcher(url);

    String iconUrl = null;
    if (m.find()) {
      iconUrl = m.group() + "favicon.ico";
    }
    if (!isVisit(iconUrl)) {
      iconUrl = null;
    }

    return iconUrl;
  }

  /**
   * 判断url是否可以访问
   *
   * @param urlStr url
   * @return 访问结果
   */
  public static boolean isVisit(String urlStr) {
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

}
