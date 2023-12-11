package com.jerry.trucker.truckerapplication.controller;


import com.jerry.trucker.truckerapplication.payload.BolDto;
import com.jerry.trucker.truckerapplication.service.BolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bol")
public class BolController {

    private BolService bolService;

    public BolController(BolService bolService) {
        this.bolService = bolService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<BolDto> generateBol(@Valid @PathVariable String id) {
        return new ResponseEntity<>(bolService.generateBol(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<BolDto>> fetchAllBol() {
        return new ResponseEntity<>(bolService.fetchAllBol(), HttpStatus.OK);
    }
}
