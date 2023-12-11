package com.jerry.trucker.truckerapplication.service.impl;


import com.jerry.trucker.truckerapplication.execptions.ResourceNotFoundException;
import com.jerry.trucker.truckerapplication.models.*;
import com.jerry.trucker.truckerapplication.payload.BolDto;
import com.jerry.trucker.truckerapplication.payload.CreateOrderDto;
import com.jerry.trucker.truckerapplication.payload.OrderDto;
import com.jerry.trucker.truckerapplication.payload.UserDto;
import com.jerry.trucker.truckerapplication.repository.*;
import com.jerry.trucker.truckerapplication.service.AddressService;
import com.jerry.trucker.truckerapplication.service.BolService;
import com.jerry.trucker.truckerapplication.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private BolRepository bolRepository;
    private BolService bolService;
    private AddressRepository addressRepository;
    private StatusRepository statusRepository;

    private ModelMapper modelMapper;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            BolRepository bolRepository,
            UserRepository userRepository,
            BolService bolService,
            AddressRepository addressRepository,
            StatusRepository statusRepository,
            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.bolRepository = bolRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.bolService = bolService;
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto createOrder(long bolID, long pickUpID, long dropOffID, CreateOrderDto createOrderDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Address Adding: " + username);
        Optional<User> user = userRepository.findByEmail(username);
        UserDto userDto = new UserDto();
        userDto.setEmail(user.get().getEmail());
        userDto.setFirst_name(user.get().getFirstName());
        userDto.setLast_name(user.get().getLastName());
        Bol bol = bolRepository.findById(bolID).orElseThrow(() -> new ResourceNotFoundException("Bol", "id", bolID));
        Address pickUpAddress = addressRepository.findById(pickUpID).orElseThrow(() -> new ResourceNotFoundException("Pickup", "id", pickUpID));
        Address dropOffAddress = addressRepository.findById(dropOffID).orElseThrow(() -> new ResourceNotFoundException("Drop Off", "id", dropOffID));
        Status status = statusRepository.findByStatus("ORDER_CREATED").orElseThrow(() -> new ResourceNotFoundException("status", "id", (long) 0));
        System.out.println("Order will be created now");
        Order order = new Order();
        order.setBol(bol);
        // update BOL as well

        order.setUser(user.get());
        order.setPickupLocation(pickUpAddress);
        order.setDropoffLocation(dropOffAddress);
        order.setPickupDate(new Date(createOrderDto.getPickupDate()));
        order.setDropoffDate(new Date(createOrderDto.getPickupDate()));
        order.setStatus(status);
        System.out.println("Order created");
        orderRepository.save(order);
        System.out.println("Order Saved Now");
        OrderDto result = modelMapper.map(order, OrderDto.class);
        result.setUserDto(userDto);
        bol.setOrder(order);
        //update order in BOL
        bolRepository.save(bol);
        return result;
    }

    @Override
    public List<OrderDto> fetchOrderDto() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).toList();
    }

    @Override
    public List<BolDto> fetchBol() {
        List<BolDto> bolDtos = bolService.fetchNullOrderBol();
        return bolDtos;
    }
}
