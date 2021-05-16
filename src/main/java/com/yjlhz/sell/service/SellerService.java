package com.yjlhz.sell.service;

import com.yjlhz.sell.dataobject.SellerInfo;

public interface SellerService {

    /**
     * 查找买家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
