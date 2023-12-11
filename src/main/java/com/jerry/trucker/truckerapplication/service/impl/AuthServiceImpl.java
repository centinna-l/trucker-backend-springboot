package com.jerry.trucker.truckerapplication.service.impl;


import com.jerry.trucker.truckerapplication.execptions.TruckerApiException;
import com.jerry.trucker.truckerapplication.models.Role;
import com.jerry.trucker.truckerapplication.models.User;
import com.jerry.trucker.truckerapplication.payload.LoginDto;
import com.jerry.trucker.truckerapplication.payload.RegisterDto;
import com.jerry.trucker.truckerapplication.repository.RoleRepository;
import com.jerry.trucker.truckerapplication.repository.UserRepository;
import com.jerry.trucker.truckerapplication.security.JwtTokenProvider;
import com.jerry.trucker.truckerapplication.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository = roleRepository;
    }

    @Override
    public String login(LoginDto loginDto) {

        System.out.println("Login Implementation" + loginDto.getPassword());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                ));

        System.out.println("After Authenticate");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateJwt(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new TruckerApiException(HttpStatus.BAD_REQUEST, "Email Exists");
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setFirstName(registerDto.getFirst_name());
        user.setLastName(registerDto.getLast_name());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_ADMIN").get();
        roles.add(userRole);
        user.setRoles(roles);
        user.setIsAdmin(true);
        userRepository.save(user);
        return "User Registered Successfully";
    }
}
