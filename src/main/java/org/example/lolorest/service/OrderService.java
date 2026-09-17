package org.example.lolorest.service;

import lombok.RequiredArgsConstructor;
import org.example.lolorest.Dto.DishStatus;
import org.example.lolorest.Dto.Order;
import org.example.lolorest.Dto.OrderStatus;
import org.example.lolorest.entity.DishEntity;
import org.example.lolorest.entity.OrderEntity;
import org.example.lolorest.entity.OrderItemEntity;
import org.example.lolorest.mapper.OrderMapper;
import org.example.lolorest.repository.DishRepository;
import org.example.lolorest.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final OrderMapper orderMapper;


    public Order createOrder() {
        OrderEntity newOrder = new OrderEntity();
        newOrder.setStatus(OrderStatus.CREATED);
        newOrder.setCreatedAt(LocalDateTime.now());
        newOrder.setItems(new ArrayList<>());

        OrderEntity order = orderRepository.save(newOrder);

        return orderMapper.toDto(order);
    }


    public Order getOrder(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));

        return orderMapper.toDto(orderEntity);
    }


    public Order addDishToOrder(Long orderId, Long dishId, Integer quantity) {

        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found by id: " + orderId));

        if(orderEntity.getStatus().equals(OrderStatus.COMPLETED) ||
                orderEntity.getStatus().equals(OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Order has already been completed or cancelled");
        }


        if(orderEntity.getStatus().equals(OrderStatus.CREATED)) {
            orderEntity.setStatus(OrderStatus.IN_PROGRESS);
        }

        DishEntity dishEntity = dishRepository.findById(dishId)
                .orElseThrow(() -> new NoSuchElementException("Dish not found by id: " + dishId));

        if(dishEntity.getDishStatus().equals(DishStatus.UNAVAILABLE)){
            throw new IllegalStateException("Dish has been unavailable");
        }

        OrderItemEntity itemEntity = new OrderItemEntity();
        itemEntity.setOrder(orderEntity);
        itemEntity.setDish(dishEntity);
        itemEntity.setQuantity(quantity);
        itemEntity.setPriceAtAddition(dishEntity.getDishPrice());
        itemEntity.setAddedAt(LocalDateTime.now());

        orderEntity.getItems().add(itemEntity);

        OrderEntity savedEntity = orderRepository.save(orderEntity);
        return orderMapper.toDto(savedEntity);
    }


    public Order deleteDishFromOrder(Long orderId, Long itemId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found by id: " + orderId));

        if(orderEntity.getStatus().equals(OrderStatus.COMPLETED)
        || orderEntity.getStatus().equals(OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Order has already been completed or cancelled");
        }

        orderEntity.getItems().removeIf(item -> item.getId().equals(itemId));

        OrderEntity savedEntity = orderRepository.save(orderEntity);
        return orderMapper.toDto(savedEntity);
    }


    public Order completeOrder(Long orderId) {

        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found by id: " + orderId));

        if(orderEntity.getStatus().equals(OrderStatus.COMPLETED)
                || orderEntity.getStatus().equals(OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Order has already been completed or cancelled");
        }

        if(orderEntity.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot complete an empty order. Add at least one dish first.");
        }

        orderEntity.setStatus(OrderStatus.COMPLETED);
        OrderEntity order = orderRepository.save(orderEntity);
        return orderMapper.toDto(order);
    }


    public Order cancelOrder(Long orderId) {

        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found by id: " + orderId));

        if(orderEntity.getStatus().equals(OrderStatus.COMPLETED)
                || orderEntity.getStatus().equals(OrderStatus.CANCELLED)) {
            throw new IllegalStateException("Order has already been completed or cancelled");
        }

        orderEntity.setStatus(OrderStatus.CANCELLED);
        OrderEntity order = orderRepository.save(orderEntity);
        return orderMapper.toDto(order);
    }

    public List<Order> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDto).toList();
    }
}
