package org.example.lolorest.repository;

import org.example.lolorest.Dto.DishStatus;
import org.example.lolorest.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {
    List<DishEntity> findAllByDishStatusNot(DishStatus status);
}
