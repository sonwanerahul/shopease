package com.rahul.shopease.DTO.Request;

import com.rahul.shopease.Enum.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateRequest {
    @NotBlank(message = "Customer Name is required")
    private String customerName;
    @NotNull(message="Gender is required")
    private Gender gender;
    @Pattern(regexp = "^[6-9]\\d{9}$",
            message="Enter Valid Number")
    private String mobileNo;

    @NotBlank(message = "Address is required")
    private String address;
}
