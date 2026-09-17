package org.example.lolorest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.lolorest.Dto.Dish;
import org.example.lolorest.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController()
@RequestMapping("/lolorest/dish")
@RequiredArgsConstructor
@Slf4j
public class DishController {
    private final DishService dishService;

    @PostMapping()
    public ResponseEntity<Dish> createDish(@Valid @RequestBody Dish dishToCreate){
        log.info("Creating dish: {}", dishToCreate);
        return ResponseEntity.ok(dishService.createDish(dishToCreate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDish( @PathVariable("id") Long id){
        log.info("Getting dish by the id: {}", id);
        return  ResponseEntity.ok(dishService.getDish(id));
    }

    @GetMapping()
    public ResponseEntity<List<Dish>> getAllDishes(){
        log.info("Getting all dishes");
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable("id") Long id , @Valid @RequestBody Dish dishToUpdate){
        log.info("Updating dish by id : {}", id);
        return ResponseEntity.ok(dishService.updateDish(id , dishToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dish> deleteDish(@PathVariable("id") Long id){
        log.info("Deleting dish by id : {}", id);
        return ResponseEntity.ok(dishService.deleteDish(id));
    }
}