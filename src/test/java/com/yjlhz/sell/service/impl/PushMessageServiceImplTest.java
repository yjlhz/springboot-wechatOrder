package com.yjlhz.sell.service.impl;

import com.yjlhz.sell.dto.OrderDTO;
import com.yjlhz.sell.service.OrderService;
import com.yjlhz.sell.service.PushMessageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PushMessageServiceImplTest {

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1620312439772504683");
        pushMessageService.orderStatus(orderDTO);
    }
}