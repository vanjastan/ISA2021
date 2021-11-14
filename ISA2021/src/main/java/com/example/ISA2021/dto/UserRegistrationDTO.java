package com.example.ISA2021.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotNull(message = "Username must be provided")
    private String username;

    @NotNull(message = "Email must be provided")
    @Email(regexp = ".+@.+\\..+", message = "Email is not valid")
    private String email;

    @NotNull(message = "Password must be provided")
    private String password;

    @NotNull(message = "Name must be provided")
    private String name;

    @NotNull(message = "Surname must be provided")
    private String surname;

    @NotNull(message = "Address must be provided")
    private String address;

    @NotNull(message = "Phone must be provided")
    private String phone;

    @NotNull(message = "City must be provided")
    private String city;

    @NotNull(message = "Country must be provided")
    private String country;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
