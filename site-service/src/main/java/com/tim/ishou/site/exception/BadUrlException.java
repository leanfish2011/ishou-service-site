package com.tim.ishou.site.exception;

import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date： 2021-01-16 下午3:39
 * @description：
 */
public class BadUrlException extends CommonException {

  public BadUrlException(String msg) {
    super(msg);
  }
}
