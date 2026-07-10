package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.CartRequest;
import com.rahul.shopease.DTO.Response.CartResponse;
import com.rahul.shopease.Entity.Cart;
import com.rahul.shopease.Entity.Customer;
import com.rahul.shopease.Exception.CartAlreadyExitsException;
import com.rahul.shopease.Exception.CartNotFoundException;
import com.rahul.shopease.Exception.CustomerNotFoundException;
import com.rahul.shopease.Repository.CartRepository;
import com.rahul.shopease.Repository.CustomerRepository;
import com.rahul.shopease.Transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository; // To save Cart
    @Autowired
    CustomerRepository customerRepository; // To find customer From Database

    //Add Cart
    public CartResponse addCart(CartRequest  cartRequest) {

        // to find customer
        Customer customer = customerRepository.findById(cartRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));

        //Already cart present?
        Optional<Cart> existingCart = cartRepository.findByCustomer(customer);
                //if already cart exits for this customer then throw this exception
                if(existingCart.isPresent()) {
                throw new CartAlreadyExitsException("Cart Already exists ");
                }

                //if cart dose not exits for this customer
        Cart cart = CartTransformer.requestToCart(cartRequest);
        cart.setCustomer(customer);
        Cart  savedCart = cartRepository.save(cart);
        return CartTransformer.cartToResponse(savedCart);
    }

    //Get Cart By ID
    public CartResponse getCartById(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new CartNotFoundException("cart not found"));
        return CartTransformer.cartToResponse(cart);

    }

    //Get Cart By CustomerId
    public CartResponse getCartByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("customer not found"));

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(()->new CartNotFoundException("Cart Not Found"));

        return CartTransformer.cartToResponse(cart);
    }

    //Get All Carts
    public List<CartResponse> getAllCarts(){
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(CartTransformer::cartToResponse).toList();
    }

    //Delete Cart
    public String deleteCart(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new CartNotFoundException("Cart Not Found "));

        Customer customer = cart.getCustomer();
        customer.setCart(null);
        cartRepository.deleteById(cartId);
        return "Cart Deleted Successfully";
    }
}
