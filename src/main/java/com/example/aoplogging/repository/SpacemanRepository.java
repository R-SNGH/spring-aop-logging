package com.example.aoplogging.repository;

import com.example.aoplogging.entities.Spaceman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpacemanRepository extends JpaRepository<Spaceman, Long> {
}
