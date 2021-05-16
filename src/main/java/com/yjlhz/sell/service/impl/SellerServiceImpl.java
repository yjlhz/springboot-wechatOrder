package com.yjlhz.sell.service.impl;

import com.yjlhz.sell.dataobject.SellerInfo;
import com.yjlhz.sell.repository.SellerInfoRepository;
import com.yjlhz.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
