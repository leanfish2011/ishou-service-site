package com.tim.ishou.site.exception;

import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date： 2021-01-16 下午8:31
 * @description：
 */
public class IllegalException extends CommonException {

  public IllegalException() {
    super("存在非法内容");
  }
}
