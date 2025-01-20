package kamenov.cupcakespakoandmoni.models.dtos;

import jakarta.validation.constraints.*;

public class UserRegisterBindingModel {
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 symbols!")
    @NotNull(message = "Username must not be empty!")
    //@UniqueUsername
    private String username;
    @NotEmpty
    @Size(min = 3,max = 20,message = "Full name must be between 3 and 20 symbols!")
    private String fullName;
    @Email(message = "Email must be valid format!")
    @NotBlank(message = "Email must not be empty!")
    //@UniqueEmail
    private String email;
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 symbols!")
    @NotNull(message = "Password must not be empty!")
    private String password;
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 symbols!")
    @NotNull(message = "Password must not be empty!")
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols!") @NotNull(message = "Username must not be empty!") String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(@Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols!") @NotNull(message = "Username must not be empty!") String username) {
        this.username = username;
        return this;
    }

    public @NotEmpty @Size(min = 3, max = 20, message = "Full name must be between 3 and 20 symbols!") String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(@NotEmpty @Size(min = 3, max = 20, message = "Full name must be between 3 and 20 symbols!") String fullName) {
        this.fullName = fullName;
        return this;
    }

    public @Email(message = "Email must be valid format!") @NotBlank(message = "Email must not be empty!") String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(@Email(message = "Email must be valid format!") @NotBlank(message = "Email must not be empty!") String email) {
        this.email = email;
        return this;
    }

    public @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols!") @NotNull(message = "Password must not be empty!") String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(@Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols!") @NotNull(message = "Password must not be empty!") String password) {
        this.password = password;
        return this;
    }

    public @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols!") @NotNull(message = "Password must not be empty!") String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(@Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols!") @NotNull(message = "Password must not be empty!") String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
