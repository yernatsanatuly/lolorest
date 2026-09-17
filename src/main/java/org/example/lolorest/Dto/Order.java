package org.example.lolorest.Dto;

import java.time.LocalDateTime;
import java.util.List;

public record Order(
        Long id,
        OrderStatus status,
        LocalDateTime createdAt,
        List<OrderItem> items,
        Double totalAmount
) {}