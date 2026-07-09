package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.CustomerRequest;
import com.rahul.shopease.DTO.Response.CustomerResponse;
import com.rahul.shopease.Entity.Customer;
import com.rahul.shopease.Exception.CustomerNotFoundException;
import com.rahul.shopease.Repository.CustomerRepository;
import com.rahul.shopease.Transformer.CustomerTrnasformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

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
    public CustomerResponse updateCustomerById(CustomerRequest customerRequest,int customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setGender(customerRequest.getGender());
        customer.setEmail(customerRequest.getEmail());
        customer.setPassword(customerRequest.getPassword());
        customer.setMobileNo(customerRequest.getMobileNo());
        customer.setAddress(customerRequest.getAddress());

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
}
