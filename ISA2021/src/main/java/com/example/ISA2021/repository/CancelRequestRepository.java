package com.example.ISA2021.repository;

import com.example.ISA2021.model.CancelRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CancelRequestRepository extends JpaRepository<CancelRequest, Long> {
    CancelRequest findOneById(Long id);
}
