import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author：tim
 * @date：20-6-20 下午7:32
 * @description：测试从url中解析网站图标
 */
public class TestUlrMatch {

  @Test
  public void testPrintMessage() {
    String[] urls = {"https://www.cnblogs.com/leanfish/", "https://tool.lu/",
        "http://www.25os.com/default.html", "https://www.shiyanlou.com/"};

    Pattern p = Pattern.compile(
        "((https?|http|ftp|file):\\/\\/)?[-A-Za-z0-9+&@#\\/%?=~_|!:,.;]+\\.[-A-Za-z+]+\\/");

    for (int i = 0, size = urls.length; i < size; i++) {
      Matcher m = p.matcher(urls[i]);

      if (m.find()) {
        System.out.println(m.group() + "favicon.ico");
      }
    }

  }
}
