package com.rahul.shopease.Transformer;
import com.rahul.shopease.DTO.Request.OrderRequest;
import com.rahul.shopease.DTO.Response.OrderResponse;
import com.rahul.shopease.Entity.Order;


public class OrderTransformer {
    public static Order requestToOrder(OrderRequest orderRequest) {
        return Order.builder().build();
    }

    public static OrderResponse orderToResponse(Order order) {
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .totalAmmount(order.getTotalAmmount())
                .customerName(order.getCustomer().getCustomerName())
                .build();
    }
}
