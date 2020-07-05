//package com.tim.ishou.site.tester;
//
//import com.tim.ishou.site.util.UrlUtil;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import net.anumbrella.seaweedfs.core.FileSource;
//import net.anumbrella.seaweedfs.core.FileTemplate;
//import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
//import org.junit.Test;
//
///**
// * @author：tim
// * @date：20-6-21 上午10:52
// * @description： 测试seaweedfs上传文件
// */
//public class TestSeaweedfs {
//
//  public FileTemplate fileTemplate() {
//    FileSource fileSource = new FileSource();
//    fileSource.setHost("127.0.0.1");
//    fileSource.setPort(9333);
//
//    try {
//      // 启动服务
//      fileSource.startup();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return new FileTemplate(fileSource.getConnection());
//  }
//
//  private FileTemplate template;
//
//  @Test
//  public void testSeaweedFS() throws IOException {
//    template = fileTemplate();
//    String filePath = "/home/tim/Downloads/github.png";
//    // 上传可以指定文件名
//    FileHandleStatus handleStatus = template.saveFileByStream("github.png",
//        new FileInputStream(new File(filePath)));
//
//    String url = handleStatus.getFileUrl();
//    System.out.println(url);
//  }
//
//  @Test
//  public void testUlrToSeaweedFS() throws Exception {
//    String url = "https://www.cnblogs.com/favicon.ico";
//    InputStream inputStream = UrlUtil.getStreamFromUrl(url);
//
//    template = fileTemplate();
//    FileHandleStatus handleStatus = template.saveFileByStream("cnblogs", inputStream);
//
//    String picUrl = handleStatus.getFileUrl();
//    System.out.println(picUrl);
//  }
//
//}
