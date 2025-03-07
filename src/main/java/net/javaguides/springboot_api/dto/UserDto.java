package net.javaguides.springboot_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotEmpty(message = "User first name should not be null or empty")
    @NotNull
    private String username;

    @NotEmpty(message = "User last name should not be null or empty")
    @NotNull
    @Email
    private String email;

    @NotEmpty(message = "User email should not be null or empty")
    @NotNull
    private String password;
}
