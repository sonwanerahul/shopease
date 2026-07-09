package com.rahul.shopease.Controller;

import com.rahul.shopease.DTO.Request.CustomerRequest;
import com.rahul.shopease.DTO.Response.CustomerResponse;
import com.rahul.shopease.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PutMapping("/{customerId}")
    public CustomerResponse updateCustomerById(@Valid @RequestBody CustomerRequest customerRequest,
                                                @PathVariable("customerId") int customerId){
        return customerService.updateCustomerById(customerRequest, customerId);
    }
    @DeleteMapping("/{customerId}")
    public String deleteCustomer(@Valid @PathVariable("customerId") int customerId){
        return customerService.deleteCustomer(customerId);
    }
}
