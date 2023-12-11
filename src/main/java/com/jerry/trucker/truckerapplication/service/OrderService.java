package com.jerry.trucker.truckerapplication.service;

import com.jerry.trucker.truckerapplication.payload.BolDto;
import com.jerry.trucker.truckerapplication.payload.CreateOrderDto;
import com.jerry.trucker.truckerapplication.payload.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(long bolID, long pickUpID, long dropOffID, CreateOrderDto createOrderDto);

    List<OrderDto> fetchOrderDto();
    List<BolDto> fetchBol();
}
