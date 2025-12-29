package com.example.ecomMyself.ecomMyself.controller;

import com.example.ecomMyself.ecomMyself.model.DTO.Order_request;
import com.example.ecomMyself.ecomMyself.model.DTO.Order_response;
import com.example.ecomMyself.ecomMyself.service.Order_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private Order_service order_service;
    @PostMapping("PlaceOrder")
    public String placeOrder(@RequestBody Order_request OrderRequest)
    {
        order_service.placeOrder(OrderRequest);
        return "Done";
    }

//    @PostMapping("PlaceOrder")
//    public void placeOrder(@RequestBody Order_request OrderRequest, @AuthenticationPrincipal UserPrincipal principal)
//    {
//        order_service.placeOrder(OrderRequest,principal);
//    }
    @GetMapping("MyOrders")
    public List<Order_response> MyOrders()
    {
        return order_service.MyOrders();
    }

//    @GetMapping("MyOrders")
//    public List<Order_response> placeOrder(@AuthenticationPrincipal UserPrincipal principal)
//    {
//        return order_service.MyOrders(principal);
//    }
    @GetMapping("MyOrders/{id}")
    public Order_response myOrderId(@RequestParam int id)
    {
        return order_service.myOrderId(id);
    }
}
