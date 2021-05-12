package com.yjlhz.sell.service.impl;

import com.yjlhz.sell.dto.OrderDTO;
import com.yjlhz.sell.service.OrderService;
import com.yjlhz.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDTO orderDTO = orderService.findOne("123456");
        payService.create(orderDTO);
    }
}