package com.jerry.trucker.truckerapplication.repository;

import com.jerry.trucker.truckerapplication.models.Address;
import com.jerry.trucker.truckerapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUser(User user);

    Optional<Address> findById(long id);
}
