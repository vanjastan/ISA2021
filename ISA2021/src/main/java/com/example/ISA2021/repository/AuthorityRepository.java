package com.example.ISA2021.repository;

import com.example.ISA2021.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}