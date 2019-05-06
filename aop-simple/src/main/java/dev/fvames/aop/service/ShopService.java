package dev.fvames.aop.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    Logger lg = Logger.getLogger(ShopService.class);

    public void buyApple(String productName, int number, Double price) {
        lg.info("购买 " + productName + ", 数量 " + number + ", 价格 " + price);
    }

}
