package com.rahul.shopease.Repository;

import com.rahul.shopease.Entity.Cart;
import com.rahul.shopease.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByCustomer(Customer customer); // To find Customer Cart

}
