package com.example.ISA2021.service;

import com.example.ISA2021.model.Boat;
import com.example.ISA2021.model.CancelRequest;

import java.util.List;

public interface CancelRequestService {
    CancelRequest save(CancelRequest cr);
    CancelRequest findById(Long id);
    List<CancelRequest> findAll();
}
