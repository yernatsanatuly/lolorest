package org.example.lolorest.mapper;

import lombok.RequiredArgsConstructor;
import org.example.lolorest.Dto.Order;
import org.example.lolorest.Dto.OrderItem;
import org.example.lolorest.entity.OrderEntity;
import org.example.lolorest.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderEntity toEntity(Order domain) {
        if (domain == null) {
            return null;
        }

        OrderEntity entity = new OrderEntity();
        entity.setId(domain.id());
        entity.setStatus(domain.status());
        entity.setCreatedAt(domain.createdAt());

        if (domain.items() != null) {
            List<OrderItemEntity> itemEntities = domain.items().stream()
                    .map(orderItemMapper::toEntity)
                    .peek(item -> item.setOrder(entity))
                    .toList();
            entity.setItems(new ArrayList<>(itemEntities));
        }

        return entity;
    }


    public Order toDto(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        List<OrderItem> items = (entity.getItems() != null)
                ? entity.getItems().stream().map(orderItemMapper::toDto).toList()
                : Collections.emptyList();

        Double totalAmount = entity.getTotalAmount();

        return new Order(
                entity.getId(),
                entity.getStatus(),
                entity.getCreatedAt(),
                items,
                totalAmount
        );
    }
}