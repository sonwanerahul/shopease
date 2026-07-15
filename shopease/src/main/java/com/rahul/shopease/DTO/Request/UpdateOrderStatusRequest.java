package com.rahul.shopease.DTO.Request;
import com.rahul.shopease.Enum.OrderStatus;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderStatusRequest {
    @Positive(message="Order Id Must Be Greather than 0")
    private int orderId;
    private OrderStatus orderStatus;
}
