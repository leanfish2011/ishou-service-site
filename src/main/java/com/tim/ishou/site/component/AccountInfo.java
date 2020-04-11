package com.tim.ishou.site.component;

import com.tim.auth.sdk.vo.TokenModel;
import com.tim.exception.type.CommonException;
import com.tim.exception.type.ServiceException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：20-4-11 上午11:27
 * @description： 存储当前用户信息
 */
@Component
@Lazy
public class AccountInfo {

  private TokenModel tokenModel;

  public TokenModel getUserInfo() throws CommonException {
    if (tokenModel != null) {
      return tokenModel;
    }

    throw new ServiceException("未找到用户");
  }

  public void setTokenModel(TokenModel tokenModel) {
    this.tokenModel = tokenModel;
  }

}
