package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.ChangePasswordRequest;
import com.rahul.shopease.DTO.Request.CustomerRequest;
import com.rahul.shopease.DTO.Request.CustomerUpdateRequest;
import com.rahul.shopease.DTO.Response.CustomerResponse;
import com.rahul.shopease.Entity.Customer;
import com.rahul.shopease.Exception.CustomerNotFoundException;
import com.rahul.shopease.Repository.CustomerRepository;
import com.rahul.shopease.Transformer.CustomerTrnasformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //Add Customer
    public CustomerResponse addCustomer(CustomerRequest customerRequest){
        Customer customer = CustomerTrnasformer.requestToCustomer(customerRequest);
        Customer  savedCustomer=customerRepository.save(customer);
        return CustomerTrnasformer.customerToResponse(savedCustomer);
    }

    //Get All Customer
    public List<CustomerResponse> getAllCustomer(){
        List<Customer> customerList = customerRepository.findAll();
        return customerList .stream().map(CustomerTrnasformer::customerToResponse).toList();
    }

    //Get Customer ById
    public CustomerResponse getCustomerById(int customerId){
        Customer customer =customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        return CustomerTrnasformer.customerToResponse(customer);
    }

    //Get Customer By Email
    public CustomerResponse getCustomerByEmail(String email){
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        return CustomerTrnasformer.customerToResponse(customer);
    }

    //Update Customer By id
    public CustomerResponse updateProfile(CustomerUpdateRequest customerUpdateRequest){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        customer.setCustomerName(customerUpdateRequest.getCustomerName());
        customer.setGender(customerUpdateRequest.getGender());
        customer.setMobileNo(customerUpdateRequest.getMobileNo());
        customer.setAddress(customerUpdateRequest.getAddress());

        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTrnasformer.customerToResponse(savedCustomer);
    }

    // Delete Customer
    public String deleteCustomer(int customerId){
        Customer customer =customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        customerRepository.delete(customer);
        return "Customer deleted Sucesfully";

    }

    public CustomerResponse getLoggedInCustomer(String email){
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        return CustomerTrnasformer.customerToResponse(customer);
    }
    public String changePassword(ChangePasswordRequest changePasswordRequest , String email){
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        boolean isMatch = passwordEncoder.matches(changePasswordRequest.getOldPassword(),
                customer.getPassword());
        if(!isMatch){
            throw new RuntimeException("Old Password is Incorrect");
        }
        customer.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        customerRepository.save(customer);
        return "Password Changed Sucessfully";
    }
}
