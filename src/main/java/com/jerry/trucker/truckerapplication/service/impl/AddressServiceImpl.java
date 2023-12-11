package com.jerry.trucker.truckerapplication.service.impl;

import com.jerry.trucker.truckerapplication.execptions.ResourceNotFoundException;
import com.jerry.trucker.truckerapplication.models.Address;
import com.jerry.trucker.truckerapplication.models.User;
import com.jerry.trucker.truckerapplication.payload.AddressDto;
import com.jerry.trucker.truckerapplication.repository.AddressRepository;
import com.jerry.trucker.truckerapplication.repository.UserRepository;
import com.jerry.trucker.truckerapplication.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {


    private AddressRepository addressRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;


    public AddressServiceImpl(
            AddressRepository addressRepository,
            UserRepository userRepository,
            ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Address Adding: " + username);
        Optional<User> user = userRepository.findByEmail(username);
        Address address = new Address();
        address.setStreet1(addressDto.getStreet1());
        address.setStreet2(addressDto.getStreet2());
        address.setCity(addressDto.getCity());
        address.setPincode(addressDto.getPincode());
        address.setPhoneNo(addressDto.getPhoneNo());
        address.setCountry(addressDto.getCountry());
        address.setUser(user.get());
        addressRepository.save(address);
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto fetchAddressByID(long id) {
        return null;
    }

    @Override
    public List<AddressDto> fetchAddressByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Address Adding: " + username);
        Optional<User> user = userRepository.findByEmail(username);
        List<Address> addresses = addressRepository.findAllByUser(user.get());

        return addresses.stream().map(address -> modelMapper.map(address, AddressDto.class)).toList();
    }
}
