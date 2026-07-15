package com.rahul.shopease.Repository;
import com.rahul.shopease.Entity.Customer;
import com.rahul.shopease.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);
}
