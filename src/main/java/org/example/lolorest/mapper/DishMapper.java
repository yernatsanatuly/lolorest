package org.example.lolorest.mapper;

import org.example.lolorest.Dto.Dish;
import org.example.lolorest.entity.DishEntity;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public DishEntity toEntity(Dish dish) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setDishName(dish.name());
        dishEntity.setDishPrice(dish.price());
        dishEntity.setDishStatus(dish.status());
        dishEntity.setCategory(dish.category());
        return dishEntity;
    }

    public Dish toDto(DishEntity dishEntity) {
        Dish dish = new Dish(
                dishEntity.getId(),
                dishEntity.getDishName(),
                dishEntity.getDishPrice(),
                dishEntity.getDishStatus(),
                dishEntity.getCategory()
        );
        return dish;
    }
}
