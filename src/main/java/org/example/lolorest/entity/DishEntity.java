package org.example.lolorest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lolorest.Dto.DishCategory;
import org.example.lolorest.Dto.DishStatus;

@Entity
@Table(name = "dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "dish_price")
    private double dishPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "dish_status")
    private DishStatus dishStatus;

    @Enumerated(EnumType.STRING)
    private DishCategory category;
}
