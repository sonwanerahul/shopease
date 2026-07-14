package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.OrderRequest;
import com.rahul.shopease.DTO.Response.OrderResponse;
import com.rahul.shopease.Entity.*;
import com.rahul.shopease.Enum.OrderStatus;
import com.rahul.shopease.Exception.CartNotFoundException;
import com.rahul.shopease.Exception.CustomerNotFoundException;
import com.rahul.shopease.Repository.*;
import com.rahul.shopease.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    //Placed order
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));

        Cart cart =customer.getCart();
        List<CartItems> cartItems =cart.getCartItems();
        if(cartItems.isEmpty()){
            throw new CartNotFoundException("Cart is Empty");
        }
        Order order = OrderTransformer.requestToOrder(orderRequest);
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalAmmount(cart.getTotalAmmount());

        Order savedOrder=orderRepository.save(order);

        for(CartItems cartItem : cartItems){
            OrderItems orderItem=OrderItems.builder().build();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            orderItemRepository.save(orderItem);
        }

        cart.getCartItems().clear();
        cart.setTotalAmmount(0);
        cartRepository.save(cart);

        return OrderTransformer.orderToResponse(savedOrder);

    }
}
