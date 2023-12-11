package com.jerry.trucker.truckerapplication.service.impl;


import com.jerry.trucker.truckerapplication.models.Bol;
import com.jerry.trucker.truckerapplication.payload.BolDto;
import com.jerry.trucker.truckerapplication.repository.BolRepository;
import com.jerry.trucker.truckerapplication.service.BolService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BolServiceImpl implements BolService {


    private ModelMapper modelMapper;
    private BolRepository bolRepository;

    public BolServiceImpl(
            BolRepository bolRepository,
            ModelMapper modelMapper) {
        this.bolRepository = bolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BolDto generateBol(String bolId) {

        Bol bol = new Bol();
        bol.setBolId(bolId.toUpperCase());
        bol.setStatus(true);

        bolRepository.save(bol);

        return modelMapper.map(bol, BolDto.class);
    }

    @Override
    public List<BolDto> fetchAllBol() {
        List<Bol> bols = bolRepository.findAll();

        return bols.stream().map(bol -> modelMapper.map(bol, BolDto.class)).toList();
    }


    // Fetch Null BOL
    @Override
    public List<BolDto> fetchNullOrderBol() {
        List<Bol> bols = bolRepository.findAll().stream().filter(bol -> bol.getOrder() == null).toList();

        return bols.stream().map(bol -> modelMapper.map(bol, BolDto.class)).toList();
    }


//    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//    private final int LENGTH = 6;
//
//
//    public String generateBolID() {
//        SecureRandom random = new SecureRandom();
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < LENGTH; i++) {
//            int randomIndex = random.nextInt(CHARACTERS.length());
//            char randomChar = CHARACTERS.charAt(randomIndex);
//            sb.append(randomChar);
//        }
//
//        return sb.toString();
//    }
}
