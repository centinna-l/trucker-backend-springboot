package com.jerry.trucker.truckerapplication.repository;

import com.jerry.trucker.truckerapplication.models.Status;
import com.jerry.trucker.truckerapplication.payload.StatusDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findByStatus(String status);
}
