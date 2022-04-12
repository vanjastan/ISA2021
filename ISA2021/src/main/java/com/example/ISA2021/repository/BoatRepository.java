package com.example.ISA2021.repository;

import com.example.ISA2021.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BoatRepository extends JpaRepository<Boat,Long> {
    Boat findByName(String name);
}
