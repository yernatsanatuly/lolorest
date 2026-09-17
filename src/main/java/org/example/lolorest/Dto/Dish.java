package org.example.lolorest.Dto;
import jakarta.validation.constraints.*;

public record Dish(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        @Positive
        Double price,
        @NotNull
        DishStatus status,
        @NotNull
        DishCategory category

) {

}
