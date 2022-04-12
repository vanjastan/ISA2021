package com.example.ISA2021.controller;

import com.example.ISA2021.dto.BoatDTO;
import com.example.ISA2021.model.Boat;
import com.example.ISA2021.repository.BoatRepository;
import com.example.ISA2021.service.BoatService;
import com.example.ISA2021.service.impl.BoatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/boats")
public class BoatController {

    @Autowired
    private BoatServiceImpl boatImpl;

    @Autowired
    private BoatService boatService;

    @Autowired
    private BoatRepository boatRepo;

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<BoatDTO>> getAllBoats() {

        List<Boat> boats = boatService.findAll();

        List<BoatDTO> boatDTO = new ArrayList<>();
        for (Boat b: boats) {
            boatDTO.add(new BoatDTO(b));
        }

        return new ResponseEntity<>(boatDTO, HttpStatus.OK);
    }
}
