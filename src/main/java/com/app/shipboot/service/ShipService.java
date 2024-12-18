package com.app.shipboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.shipboot.exception.ResourceNotFoundException;
import com.app.shipboot.model.Ship;
import com.app.shipboot.repository.ShipRepository;

@Service
public class ShipService {
    
    @Autowired
    private ShipRepository shipRepository;

    //Get all ships
    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    //Get ship by id
    @Cacheable("ships")
    public Ship getShipById(Long id) {
        return shipRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ship not found with id: " + id));
    }

    //Save a ship
    public Ship saveShip(Ship ship) {
        return shipRepository.save(ship);
    }

    //Get all pageable ships
    public Page<Ship> getAllShips(Pageable pageable) {
        return shipRepository.findAll(pageable);
    }

    // Get ships by name
    public List<Ship> getShipsByName(String name) {
        return shipRepository.findByNameContaining(name);
    }

    // Update ship
    @CacheEvict(value = "ships", key = "#id")
    public Ship updateShip(Long id, Ship shipDetails) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with id " + id));
        
        ship.setName(shipDetails.getName());
        ship.setSpeed(shipDetails.getSpeed());
        ship.setFuelCapacity(shipDetails.getFuelCapacity());
        ship.setCargoCapacity(shipDetails.getCargoCapacity());

        return shipRepository.save(ship);
    }

    //Delete a ship by id
    @CacheEvict(value = "ships", key = "#id")
    public void deleteShip(Long id) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with id: " + id));
    
        shipRepository.deleteById(id);
    }
    
}
