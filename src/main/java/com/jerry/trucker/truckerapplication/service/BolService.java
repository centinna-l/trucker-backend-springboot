package com.jerry.trucker.truckerapplication.service;

import com.jerry.trucker.truckerapplication.payload.BolDto;

import java.util.List;

public interface BolService {
    BolDto generateBol(String bolId);

    List<BolDto> fetchAllBol();

    List<BolDto> fetchNullOrderBol();




}
