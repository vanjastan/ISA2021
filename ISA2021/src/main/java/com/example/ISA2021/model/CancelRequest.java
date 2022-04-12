package com.example.ISA2021.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cancel_requests")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CancelRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private long userId;

    @Column(name = "text")
    private String text;

    @Column(name = "accepted")
    private boolean accepted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public CancelRequest(Long id, long userId, String text, boolean accepted) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.accepted = accepted;
    }

    public CancelRequest() {

    }
}
