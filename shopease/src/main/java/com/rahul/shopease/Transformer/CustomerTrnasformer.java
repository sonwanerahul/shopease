package com.rahul.shopease.Transformer;

import com.rahul.shopease.DTO.Request.CustomerRequest;
import com.rahul.shopease.DTO.Response.CustomerResponse;
import com.rahul.shopease.Entity.Customer;

public class CustomerTrnasformer {

    public static Customer requestToCustomer(CustomerRequest request){
        return Customer.builder()
                .customerName(request.getCustomerName())
                .gender(request.getGender())
                .email(request.getEmail())
                .password(request.getPassword())
                .mobileNo(request.getMobileNo())
                .address(request.getAddress())
                .build();
    }

    public static CustomerResponse customerToResponse(Customer customer){
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .gender(customer.getGender())
                .email(customer.getEmail())
                .mobileNo(customer.getMobileNo())
                .address(customer.getAddress())
                .build();
    }
}
