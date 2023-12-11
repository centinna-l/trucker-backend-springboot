package com.jerry.trucker.truckerapplication.controller;


import com.jerry.trucker.truckerapplication.payload.AddressDto;
import com.jerry.trucker.truckerapplication.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {


    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@Valid @RequestBody AddressDto addressDto) {
        return new ResponseEntity<>(addressService.addAddress(addressDto), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<AddressDto>> fetchAllAddressByUser() {
        return new ResponseEntity<>(addressService.fetchAddressByUser(), HttpStatus.OK);
    }
}
