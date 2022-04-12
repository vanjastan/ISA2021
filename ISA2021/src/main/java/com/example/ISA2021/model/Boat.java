package com.example.ISA2021.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "boats")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Boat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "grade")
    private Long grade;

    @ElementCollection(targetClass=String.class)
    private List<Date> availableTerms;

    @Column (name = "price")
    private Long price;

    @ElementCollection(targetClass=String.class)
    private List<String> services;

    public Boat(Long id, String name, String address, String description, Long grade, List<Date> availableTerms, Long price, List<String> services) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.grade = grade;
        this.availableTerms = availableTerms;
        this.price = price;
        this.services = services;
    }

    public Boat() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public List<Date> getAvailableTerms() {
        return availableTerms;
    }

    public void setAvailableTerms(List<Date> availableTerms) {
        this.availableTerms = availableTerms;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }


}
