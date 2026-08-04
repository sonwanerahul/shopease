package com.rahul.shopease.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {
    @NotBlank(message = "Old password is reqiured")
    private String oldPassword;
    @NotBlank(message ="New password is required" )
    private String newPassword;
}
