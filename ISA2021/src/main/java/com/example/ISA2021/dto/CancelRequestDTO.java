package com.example.ISA2021.dto;

import com.example.ISA2021.model.CancelRequest;

public class CancelRequestDTO {

    private Long id;
    private Long userId;
    private String text;
    private boolean accepted;

    public CancelRequestDTO(CancelRequest cr) {
        this.id = cr.getId();
        this.userId = cr.getUserId();
        this.text = cr.getText();
        this.accepted = cr.isAccepted();
    }

    public CancelRequestDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
