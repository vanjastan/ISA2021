package com.example.ISA2021.dto;

public class UserTokenDTO {

    private String accessToken;
    private Long expiresIn;

    public UserTokenDTO() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenDTO(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
