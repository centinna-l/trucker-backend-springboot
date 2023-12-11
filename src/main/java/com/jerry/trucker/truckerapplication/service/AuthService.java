package com.jerry.trucker.truckerapplication.service;

import com.jerry.trucker.truckerapplication.payload.LoginDto;
import com.jerry.trucker.truckerapplication.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
