package com.jerry.trucker.truckerapplication.repository;

import com.jerry.trucker.truckerapplication.models.Bol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BolRepository extends JpaRepository<Bol, Long> {
    boolean existsByBolId(String bolId);

    Bol findByBolId(String bolId);

    Optional<Bol> findById(long id);

    void deleteByBolId(String bolId);

    // to fetch all active or inactive status.
    List<Bol> findAllByStatus(boolean status);


}
