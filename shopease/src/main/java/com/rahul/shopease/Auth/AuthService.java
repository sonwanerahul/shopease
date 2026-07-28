package com.rahul.shopease.Auth;

import com.rahul.shopease.DTO.Request.CustomerRequest;
import com.rahul.shopease.DTO.Request.LoginRequest;
import com.rahul.shopease.DTO.Response.CustomerResponse;
import com.rahul.shopease.DTO.Response.LoginResponse;
import com.rahul.shopease.Entity.Customer;
import com.rahul.shopease.Enum.Role;
import com.rahul.shopease.Exception.EmailAlreadyExistsException;
import com.rahul.shopease.Repository.CustomerRepository;
import com.rahul.shopease.Security.JwtService;
import com.rahul.shopease.Transformer.CustomerTrnasformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    public CustomerResponse register(CustomerRequest customerRequest) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customerRequest.getEmail());
        if (existingCustomer.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Customer newCustomer = CustomerTrnasformer.requestToCustomer(customerRequest);
        newCustomer.setRole(Role.CUSTOMER);
        newCustomer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        Customer savedCustomer = customerRepository.save(newCustomer);

        return CustomerTrnasformer.customerToResponse(savedCustomer);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Customer customer = customerRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new RuntimeException("Invalid Email"));

        boolean isMatch=passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword());
        if(!isMatch){
            throw new RuntimeException("Invalid Password");
        }
        String token = jwtService.generateToken(customer.getEmail());

        LoginResponse loginResponse =LoginResponse.builder()
                .token(token)
                .message("Login Successful")
                .build();

        return loginResponse;
    }
}
