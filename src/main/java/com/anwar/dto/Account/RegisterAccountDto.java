package com.anwar.dto.Account;

import com.anwar.validation.ConfirmPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter @Setter
@ConfirmPassword(message = "Password does not match", password = "password", passwordConfirmation = "confirmPassword")
@Data
public class RegisterAccountDto implements Serializable {
    @NotBlank(message = "Username is required")
    @Size(min = 5, message = "Username is too short")
    private final String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password is too short")
    private final String password;

    @NotBlank(message = "Password Confirmation is required")
    private final String confirmPassword;

    @NotBlank(message = "First Name is required")
    private final String firstName;

    private final String lastName;

    private final BigDecimal balance;

    private final String address;

    @NotBlank(message = "Email is required")
    @Email
    private final String email;
}
