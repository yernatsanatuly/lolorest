package org.example.lolorest.Dto;

import java.time.LocalDateTime;

public record OrderItem(
        Long id,
        Dish dish,
        Integer quantity,
        LocalDateTime addedAt,
        Double itemTotal
) {}