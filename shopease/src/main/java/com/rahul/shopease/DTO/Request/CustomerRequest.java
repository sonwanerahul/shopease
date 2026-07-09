package com.rahul.shopease.DTO.Request;

import com.rahul.shopease.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    @NotBlank(message = "Customer Name is required")
    private String customerName;

    @NotNull(message="Gender is required")
    private Gender gender;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter Valid Email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Pattern(regexp = "^[6-9]\\d{9}$",
             message="Enter Valid Number")
    private String mobileNo;

    @NotBlank(message = "Address is required")
    private String address;
}
