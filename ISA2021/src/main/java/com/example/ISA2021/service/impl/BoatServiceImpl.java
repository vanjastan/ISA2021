package com.example.ISA2021.service.impl;

import com.example.ISA2021.model.Boat;
import com.example.ISA2021.repository.BoatRepository;
import com.example.ISA2021.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatServiceImpl implements BoatService {
    @Autowired
    BoatRepository boatRepository;

    public List<Boat> findAll() throws AccessDeniedException {
        List<Boat> result = boatRepository.findAll();
        return result;
    }
}
