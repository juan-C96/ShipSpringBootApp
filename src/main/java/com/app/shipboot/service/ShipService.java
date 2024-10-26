package com.app.shipboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shipboot.model.Ship;
import com.app.shipboot.repository.ShipRepository;

@Service
public class ShipService {
    
    @Autowired
    private ShipRepository shipRepository;

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    public Optional<Ship> getShipById(Long id) {
        return shipRepository.findById(id);
    }

    public Ship saveShip(Ship ship) {
        return shipRepository.save(ship);
    }

    public void deleteById(Long id) {
        shipRepository.deleteById(id);
    }
}
