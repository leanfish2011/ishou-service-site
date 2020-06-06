package com.tim.ishou.site.component;

import com.tim.auth.sdk.vo.TokenModel;
import com.tim.ishou.site.exception.NotFoundException;
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

  public TokenModel getUserInfo() {
    if (tokenModel == null) {
      throw new NotFoundException("未找到用户");
    }

    return tokenModel;
  }

  public void setTokenModel(TokenModel tokenModel) {
    this.tokenModel = tokenModel;
  }

}
