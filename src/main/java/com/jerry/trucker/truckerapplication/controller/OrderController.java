package com.jerry.trucker.truckerapplication.controller;


import com.jerry.trucker.truckerapplication.payload.BolDto;
import com.jerry.trucker.truckerapplication.payload.CreateOrderDto;
import com.jerry.trucker.truckerapplication.payload.OrderDto;
import com.jerry.trucker.truckerapplication.service.BolService;
import com.jerry.trucker.truckerapplication.service.OrderService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/bol")
    public ResponseEntity<List<BolDto>> fetchBol() {
        return new ResponseEntity<>(orderService.fetchBol(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(
            @RequestParam(name = "bolId") long bolID,
            @RequestParam(name = "pickUpID") long pickUpID,
            @RequestParam(name = "dropOffID") long dropOffID,
            @Valid @RequestBody CreateOrderDto createOrderDto
    ) {
        return new ResponseEntity<>(orderService.createOrder(bolID, pickUpID, dropOffID, createOrderDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderDto>> fetchAllOrders() {
        return new ResponseEntity<>(orderService.fetchOrderDto(), HttpStatus.OK);
    }
}
