package com.rahul.shopease.DTO.Request;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRequest {
    @Positive(message="Customer Id Must Be Greather than 0")
    private int customerId;
}
