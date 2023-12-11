package com.jerry.trucker.truckerapplication.repository;

import com.jerry.trucker.truckerapplication.models.Address;
import com.jerry.trucker.truckerapplication.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
