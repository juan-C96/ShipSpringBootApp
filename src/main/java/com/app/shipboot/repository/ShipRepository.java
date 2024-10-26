package com.app.shipboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.shipboot.model.Ship;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
