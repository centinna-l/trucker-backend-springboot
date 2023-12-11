package com.jerry.trucker.truckerapplication.repository;

import com.jerry.trucker.truckerapplication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}

