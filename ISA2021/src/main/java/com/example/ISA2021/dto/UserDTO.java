package com.example.ISA2021.dto;

import com.example.ISA2021.model.Authority;
import com.example.ISA2021.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String address;
    private String city;
    private String country;
    private String number;
    private String password;
    private String email;
    private String roleType;
    private List<String> authorities;
    private UserTokenDTO token;

    public UserDTO() {
    }

    public UserDTO(User u) {
        id = u.getId();
        name = u.getName();
        surname = u.getSurname();
        address = u.getAddress();
        city = u.getCity();
        country = u.getCountry();
        number = u.getNumber();
        password = u.getPassword();
        email = u.getEmail();
        username = u.getUsername();
        roleType = u.getRoleType();
        this.token = null;
        this.authorities = u.getAuthorities().stream()
                .map(authority -> ((Authority) authority).getName()).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public UserTokenDTO getToken() {
        return token;
    }

    public void setToken(UserTokenDTO token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
