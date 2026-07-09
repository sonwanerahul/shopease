package com.rahul.shopease.DTO.Response;
import com.rahul.shopease.Enum.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerResponse {
    private int customerId;
    private String customerName;
    private Gender gender;
    private String email;
    private String mobileNo;
    private String address;
}
