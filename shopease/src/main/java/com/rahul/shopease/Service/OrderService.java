package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.OrderRequest;
import com.rahul.shopease.DTO.Request.UpdateOrderStatusRequest;
import com.rahul.shopease.DTO.Response.OrderResponse;
import com.rahul.shopease.Entity.*;
import com.rahul.shopease.Enum.OrderStatus;
import com.rahul.shopease.Exception.CartNotFoundException;
import com.rahul.shopease.Exception.CustomerNotFoundException;
import com.rahul.shopease.Exception.OrderAlreadyDeliverdException;
import com.rahul.shopease.Exception.OrderNotFoundException;
import com.rahul.shopease.Repository.*;
import com.rahul.shopease.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            orderItem.setProductName(cartItem.getProduct().getProductName());

            orderItemRepository.save(orderItem);
        }

        cart.getCartItems().clear();
        cart.setTotalAmmount(0);
        cartRepository.save(cart);

        return OrderTransformer.orderToResponse(savedOrder);

    }
    //Get Order By ID
    public OrderResponse getOderById(int orderId){
        Order order =orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("Order Not Found"));
        return OrderTransformer.orderToResponse(order);
    }

    //Get All Order
    public List<OrderResponse> getAllOrders(){
        List<Order> orders =orderRepository.findAll();
        List<OrderResponse> orderResponse = new ArrayList<>();
        for(Order order : orders)
        {
            orderResponse.add(OrderTransformer.orderToResponse(order));
        }
        return orderResponse;
    }

    //Get Order By Customer
    public List<OrderResponse> getAllOrdersByCustomer(int customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));

        List<Order> orders= orderRepository.findByCustomer(customer);
        List<OrderResponse> orderResponse = new ArrayList<>();
        for(Order order : orders)
        {
            orderResponse.add(OrderTransformer.orderToResponse(order));
        }
        return orderResponse;
    }

    //Upadte Order Status
    public OrderResponse updateOrderStatus(UpdateOrderStatusRequest request){
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found"));
        order.setOrderStatus(request.getOrderStatus());

        Order saveOrder=orderRepository.save(order);
        return OrderTransformer.orderToResponse(saveOrder);
    }

    //cancel Order
    public OrderResponse cancelOrder(int orderId) {
        Order order= orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found"));

        if(order.getOrderStatus().equals(OrderStatus.DELIVERED)){
            throw new OrderAlreadyDeliverdException("Delivered order cannot be cancel");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        List<OrderItems> orderItems =order.getOrderItems();
        for(OrderItems orderItem : orderItems) {
            Product product = orderItem.getProduct();
            int quantity = orderItem.getQuantity();
            product.setStock(product.getStock() + quantity);
            productRepository.save(product);
        }
        Order saveOrder =orderRepository.save(order);
        return OrderTransformer.orderToResponse(saveOrder);
    }
}
