package com.rahul.shopease.Repository;
import com.rahul.shopease.Entity.Cart;
import com.rahul.shopease.Entity.CartItems;
import com.rahul.shopease.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Integer>{
    Optional<CartItems> findByCartAndProduct (Cart cart, Product product);
}
