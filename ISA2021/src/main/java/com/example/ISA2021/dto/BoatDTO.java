package com.example.ISA2021.dto;

import com.example.ISA2021.model.Boat;

import java.util.Date;
import java.util.List;

public class BoatDTO {

        private Long id;
        private String name;
        private String address;
        private String description;
        private Long grade;
        private List<Date> avaiableTerms;
        private Long price;
        private List<String> services;

    public BoatDTO(Boat b) {
        this.id = b.getId();
        this.name = b.getName();
        this.address = b.getAddress();
        this.description = b.getDescription();
        this.grade = b.getGrade();
        this.avaiableTerms = b.getAvailableTerms();
        this.price = b.getPrice();
        this.services = b.getServices();
    }

    public BoatDTO() {

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
        return avaiableTerms;
    }

    public void setAvailableTerms(List<Date> availableTerms) {
        this.avaiableTerms = availableTerms;
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
