package com.rahul.shopease.DTO.Response;

import com.rahul.shopease.Enum.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private int orderId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private double totalAmmount;
    private String customerName;
}
