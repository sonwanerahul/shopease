package com.rahul.shopease.Controller;

import com.rahul.shopease.DTO.Request.ChangePasswordRequest;
import com.rahul.shopease.DTO.Request.CustomerRequest;
import com.rahul.shopease.DTO.Request.CustomerUpdateRequest;
import com.rahul.shopease.DTO.Response.CustomerResponse;
import com.rahul.shopease.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponse addCustomer(@Valid @RequestBody  CustomerRequest customerRequest){
        return customerService.addCustomer(customerRequest);
    }

    @GetMapping("/all")
    public List<CustomerResponse> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @GetMapping("/id/{customerId}")
    public CustomerResponse getCustomerById(@Valid @PathVariable("customerId") int customerId){
        return customerService.getCustomerById(customerId);
    }
    @GetMapping("/email/{email}")
    public CustomerResponse getCustomerByEmail(@Valid @PathVariable("email") String email){
        return customerService.getCustomerByEmail(email);
    }
    @PutMapping("/update")
    public CustomerResponse updateCustomerProfile(@Valid @RequestBody CustomerUpdateRequest customerUpadteRequest){
        return customerService.updateProfile(customerUpadteRequest);
    }
    @DeleteMapping("/{customerId}")
    public String deleteCustomer(@Valid @PathVariable("customerId") int customerId){
        return customerService.deleteCustomer(customerId);
    }
    @GetMapping("/profile")
    public CustomerResponse getCustomerProfile(Authentication authentication){
        String email = authentication.getName();
        return customerService.getLoggedInCustomer(email);
    }
    @PutMapping("/change-password")
    public String changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest ,
                                 Authentication authentication){
        String email = authentication.getName();
        return customerService.changePassword(changePasswordRequest, email);

    }
}
