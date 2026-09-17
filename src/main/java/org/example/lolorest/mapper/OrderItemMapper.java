package org.example.lolorest.mapper;

import lombok.RequiredArgsConstructor;
import org.example.lolorest.Dto.OrderItem;
import org.example.lolorest.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    private final DishMapper dishMapper;

    public OrderItemEntity toEntity(OrderItem domain) {
        if (domain == null) {
            return null;
        }

        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(domain.id());
        entity.setDish(dishMapper.toEntity(domain.dish()));
        entity.setQuantity(domain.quantity());
        entity.setAddedAt(domain.addedAt());

        if (domain.dish() != null) {
            entity.setPriceAtAddition(domain.dish().price());
        }

        return entity;
    }


    public OrderItem toDto(OrderItemEntity entity) {
        if (entity == null) {
            return null;
        }

        Double itemTotal = (entity.getPriceAtAddition() != null && entity.getQuantity() != null)
                ? entity.getPriceAtAddition() * entity.getQuantity()
                : 0.0;

        return new OrderItem(
                entity.getId(),
                dishMapper.toDto(entity.getDish()),
                entity.getQuantity(),
                entity.getAddedAt(),
                itemTotal
        );
    }
}