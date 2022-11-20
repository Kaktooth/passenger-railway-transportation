package com.team.passengerrailwaytransportation.service;

import com.liqpay.LiqPay;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LiqPayApiService {

  @Value("${liqpay.key.public}")
  private String PUBLIC_KEY;
  @Value("${liqpay.key.private}")
  private String PRIVATE_KEY;

  public LiqPayApiService() {
    // TODO document why this constructor is empty
  }

  public String buyTicket(String amount, String description, String orderId) {
    Map<String, String> params = new HashMap<>();
    params.put("action", "pay");
    params.put("amount", amount);
    params.put("currency", "UAH");
    params.put("description", "Ви придбали місце:" + description);
    params.put("order_id", orderId);
    params.put("version", "3");
    LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
    return liqpay.cnb_form(params);
  }
}
