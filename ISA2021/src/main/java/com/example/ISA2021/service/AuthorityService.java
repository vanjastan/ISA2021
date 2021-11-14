package com.example.ISA2021.service;

import com.example.ISA2021.model.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
	List<Authority> findById(Long id);
	List<Authority> findByname(String name);
}
