package com.example.ISA2021.model;

import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@NoArgsConstructor
@Entity
public class Admin extends User{
    @ElementCollection(targetClass=String.class)
    private List<String> services;
}
