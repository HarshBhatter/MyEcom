package com.example.ecomMyself.ecomMyself.model.DTO;

import java.math.BigDecimal;

public record Order_item_response(
        String productName,
        int quantity,
        BigDecimal totalPrice
) {}
