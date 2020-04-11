package com.tim.ishou.site.component;

import com.tim.auth.sdk.feign.AccountFeignClient;
import com.tim.auth.sdk.vo.TokenModel;
import com.tim.message.MainCode;
import com.tim.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：20-4-11 上午11:27
 * @description： 存储当前用户信息
 */
@Component
public class AccountInfo {

  @Autowired
  private AccountFeignClient accountFeignClient;

  private TokenModel tokenModel;

  public TokenModel getUserInfo() {
    if (tokenModel != null) {
      return tokenModel;
    }

    Message<TokenModel> tokenModelMessage = accountFeignClient.profile();
    if (tokenModelMessage.getCode().equals(MainCode.SUCCESS)) {
      tokenModel = tokenModelMessage.getData();
      return tokenModelMessage.getData();
    }

    return null;
  }
}
