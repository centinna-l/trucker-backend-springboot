package com.jerry.trucker.truckerapplication.service;

import com.jerry.trucker.truckerapplication.models.Address;
import com.jerry.trucker.truckerapplication.payload.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto addAddress(AddressDto addressDto);

    AddressDto fetchAddressByID(long id);

    List<AddressDto> fetchAddressByUser();
}
