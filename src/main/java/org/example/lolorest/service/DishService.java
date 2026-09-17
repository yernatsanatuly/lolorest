package org.example.lolorest.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lolorest.Dto.Dish;
import org.example.lolorest.Dto.DishStatus;
import org.example.lolorest.entity.DishEntity;
import org.example.lolorest.mapper.DishMapper;
import org.example.lolorest.repository.DishRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;


    public Dish createDish(Dish dishToCreate) {

        DishEntity entityToSave = dishMapper.toEntity(dishToCreate);
        DishEntity savedEntity = dishRepository.save(entityToSave);

        return dishMapper.toDto(savedEntity);
    }

    public Dish getDish(Long id) {
        DishEntity dishEntity = dishRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dish with id " + id + " does not exist"));
        return dishMapper.toDto(dishEntity);
    }

    public List<Dish> getAllDishes() {
        List<DishEntity> allDishes = dishRepository.findAllByDishStatusNot(
                DishStatus.ARCHIVED
        );
        return allDishes.stream().map(dishMapper::toDto).toList();
    }


    public Dish updateDish(Long id, @Valid Dish dishToUpdate) {
        DishEntity existingDish = dishRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dish with id " + id + " does not exist"));

        existingDish.setDishName(dishToUpdate.name());
        existingDish.setDishPrice(dishToUpdate.price());
        existingDish.setDishStatus(dishToUpdate.status());
        DishEntity updatedEntity = dishRepository.save(existingDish);
        return dishMapper.toDto(updatedEntity);
    }

    public  Dish deleteDish(Long id) {

        DishEntity dishEntity = dishRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dish with id " + id + " does not exist"));

        dishEntity.setDishStatus(DishStatus.ARCHIVED);
        dishRepository.save(dishEntity);
        return dishMapper.toDto(dishEntity);
        
    }
}
