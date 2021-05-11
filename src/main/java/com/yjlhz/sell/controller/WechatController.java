package com.yjlhz.sell.controller;

import com.yjlhz.sell.enums.ResultEnum;
import com.yjlhz.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String url = "http://yjlhz.nat300.top/sell/wechat/userInfo";
        String redirectUrl = wxMpService.getOAuth2Service().buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
        WxOAuth2AccessToken wxMpOAuth2AccessToken = new WxOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权异常】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
