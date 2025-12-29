package com.example.ecomMyself.ecomMyself.service;

import com.example.ecomMyself.ecomMyself.model.*;
import com.example.ecomMyself.ecomMyself.model.DTO.Order_item_request;
import com.example.ecomMyself.ecomMyself.model.DTO.Order_item_response;
import com.example.ecomMyself.ecomMyself.model.DTO.Order_request;
import com.example.ecomMyself.ecomMyself.model.DTO.Order_response;
import com.example.ecomMyself.ecomMyself.repository.Orders_repo;
import com.example.ecomMyself.ecomMyself.repository.Product_Repo;
import com.example.ecomMyself.ecomMyself.repository.Product_colors_repo;
import com.example.ecomMyself.ecomMyself.repository.Product_size_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Order_service {
    @Autowired
    private Orders_repo ordersRepo;
    @Autowired
    private Product_Repo productRepo;
    @Autowired
    private Product_colors_repo productColorsRepo;
    @Autowired
    private Product_size_repo productSizeRepo;
    @Transactional
    public void placeOrder(Order_request request)
    {
        Orders o=new Orders();
//        o.setUserId(principal.getUser().getId());
        BigDecimal total= BigDecimal.valueOf(0);
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        o.setOrderId(orderId);
        o.setOrderDate(LocalDate.now());
        o.setStatus("Order Placed");
        List<Order_items> oi=new ArrayList<>();
        for(Order_item_request oir:request.orderItemRequests())
        {
            System.out.println(oir.productid()+" "+oir.color()+" "+oir.size()+" "+oir.quantity());
            Order_items oi2=new Order_items();
            Product product=productRepo.findById(oir.productid())
                .orElseThrow(() -> new RuntimeException("Product not found"));

            oi2.setColor(oir.color());
            oi2.setSize(oir.size());
            oi2.setQuantity(oir.quantity());
            oi2.setProduct(product);
            oi2.setOrders(o);
            total=total.add((product.getPrice().multiply(BigDecimal.valueOf(oir.quantity()))));
            oi.add(oi2);

            int updated = productSizeRepo.reduceStock(
                    oir.productid(),
                    oir.color(),
                    oir.size(),
                    oir.quantity()
            );

            int quantityLeft=(productSizeRepo.findBySizeAndProductColors_ColorAndProductColors_Product_Id(oir.size(), oir.color(), oir.productid())).orElseThrow(() -> new RuntimeException("Not Found")).getQuantity();
            if(quantityLeft==0)
            {
                productSizeRepo.deleteWhenStockOver(
                        oir.productid(),
                        oir.color(),
                        oir.size()
                );
            }

        }
        o.setTotal(total);
        o.setOrderItems(oi);
        ordersRepo.save(o);
    }


//    @Transactional
//    public void placeOrder(Order_request request,UserPrincipal principal)
//    {
//        Orders o=new Orders();
//        o.setUserId(principal.getUser().getId());
//        o.setOrderDate(LocalDate.now());
//        o.setStatus("Order Placed");
//        List<Order_items> oi=new ArrayList<>();
//        BigDecimal total=BigDecimal.ZERO;
//        for(Order_item_request oir:request.orderItemRequests())
//        {
//            Order_items oi2=new Order_items();
//            Product product=productRepo.findById(oir.productid())
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//            oi2.setColor(oir.color());
//            oi2.setSize(oir.size());
//            oi2.setQuantity(oir.quantity());
//            oi2.setProduct(product);
//            oi2.setOrders(o);
//            total=total.add((product.getPrice().multiply(BigDecimal.valueOf(oir.quantity()))));
//            oi.add(oi2);
//
//            int updated = productSizeRepo.reduceStock(
//                    oir.productid(),
//                    oir.color(),
//                    oir.size(),
//                    oir.quantity()
//            );
//
//            int quantityLeft=productSizeRepo.findBySizeAndProductColors_ColorAndProductColors_Product_Id(oir.size(),oir.color(),oir.productid());
//            if(quantityLeft==0)
//            {
//                productSizeRepo.deleteWhenStockOver(
//                        oir.productid(),
//                        oir.color(),
//                        oir.size()
//                );
//            }
//
//        }
//        o.setTotal(total);
//        o.setOrderItems(oi);
//        ordersRepo.save(o);
//    }
    public List<Order_response> MyOrders() {

        List<Orders> orders=ordersRepo.findAllByUserId(0);
        if(orders.size()==0)
            throw new RuntimeException("No Orders placed");
        List<Order_response> orderResponseList=new ArrayList<>();
        for(Orders o:orders)
        {
            String orderid=o.getOrderId();
            String status=o.getStatus();
            LocalDate orderDate=o.getOrderDate();
            BigDecimal total=o.getTotal();
            List<Order_item_response> orderItemResponseList=new ArrayList<>();
            for(Order_items o2:o.getOrderItems())
            {
                String productName=o2.getProduct().getName();
                int quantity=o2.getQuantity();
                BigDecimal total2=o2.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity));
                Order_item_response orderItemResponse=new Order_item_response(productName,quantity,total2);
                orderItemResponseList.add(orderItemResponse);
            }
            Order_response orderResponse=new Order_response(orderid,status,orderDate,orderItemResponseList,total);
            orderResponseList.add(orderResponse);
        }
        return (orderResponseList);
    }


//    public List<Order_response> MyOrders(UserPrincipal principal) {
//
//        List<Orders> orders=ordersRepo.findAllByUserId(principal.getUser().getId());
//        if(orders.size()==0)
//            throw new RuntimeException("No Orders placed");
//        List<Order_response> orderResponseList=new ArrayList<>();
//        for(Orders o:orders)
//        {
//            String orderid=o.getOrderId();
//            String status=o.getStatus();
//            LocalDate orderDate=o.getOrderDate();
//            BigDecimal total=o.getTotal();
//            List<Order_item_response> orderItemResponseList=new ArrayList<>();
//            for(Order_items o2:o.getOrderItems())
//            {
//                String productName=o2.getProduct().getName();
//                int quantity=o2.getQuantity();
//                BigDecimal total2=o2.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity));
//                Order_item_response orderItemResponse=new Order_item_response(productName,quantity,total2);
//                orderItemResponseList.add(orderItemResponse);
//            }
//            Order_response orderResponse=new Order_response(orderid,customerName,status,orderDate,orderItemResponseList,total);
//            orderResponseList.add(orderResponse);
//        }
//        return (orderResponseList);
//    }
    public Order_response myOrderId(int id)
    {
        Orders o=ordersRepo.findById(id).orElseThrow(()->new RuntimeException("No Such Order"));
        String orderId=o.getOrderId();
        String status=o.getStatus();
        LocalDate date=o.getOrderDate();
        BigDecimal total=o.getTotal();
        List<Order_item_response> orderItemResponseList=new ArrayList<>();
        for(Order_items o2:o.getOrderItems())
        {
            String productName=o2.getProduct().getName();
            int quantity=o2.getQuantity();
            BigDecimal total2=o2.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity));
            Order_item_response orderItemResponse=new Order_item_response(productName,quantity,total2);
            orderItemResponseList.add(orderItemResponse);
        }
        Order_response orderResponse=new Order_response(orderId,status,date,orderItemResponseList,total);
        return orderResponse;
    }
}
