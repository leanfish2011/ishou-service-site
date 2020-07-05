package com.tim.ishou.site.tester;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author：tim
 * @date：20-6-21 下午9:40
 * @description： 从seaweedfs地址中解析fileid
 */
public class TestFileId {

  @Test
  public void testGetFileId() {
    String fileUrl = "http://172.17.0.1:8080/4,13563fcbc5";
    String expectedFileId = "4,13563fcbc5";

    String getFiledId = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    Assert.assertEquals(expectedFileId, getFiledId);
  }
}
