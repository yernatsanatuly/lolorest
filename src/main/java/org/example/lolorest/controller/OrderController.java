package org.example.lolorest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.lolorest.Dto.Order;
import org.example.lolorest.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/lolorest/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder() {
        log.info("createNewOrder" );
        return ResponseEntity.ok(orderService.createOrder());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id){
        log.info("getOrderById {}", id);
        return ResponseEntity.ok(orderService.getOrder(id));
    }


    @PostMapping("/{orderId}/items")
    public ResponseEntity<Order> addDishToOrder(
            @PathVariable Long orderId,
            @RequestParam Long dishId,
            @RequestParam Integer quantity){
        log.info("addDishToOrder {}", orderId);

        return ResponseEntity.ok(orderService.addDishToOrder(orderId,dishId,quantity));

    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Order> deleteDishFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long itemId){

        log.info("deleteDishFromOrder {}", orderId);
        return ResponseEntity.ok(orderService.deleteDishFromOrder(orderId,itemId));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Order> completeOrder(@PathVariable Long id){

        log.info("completeOrder {}", id);
        return ResponseEntity.ok(orderService.completeOrder(id));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id){
        log.info("cancelOrder {}", id);
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}
