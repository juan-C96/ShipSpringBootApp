package com.app.shipboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.shipboot.model.Ship;
import com.app.shipboot.service.ShipService;

@RestController
@RequestMapping("/api/ships")
@CrossOrigin(origins = "http://localhost:4200")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping
    public List<Ship> getAllShips() {
        return shipService.getAllShips();
    }

    @GetMapping("/{id}")
    public Ship getShipById(@PathVariable Long id) {
        return shipService.getShipById(id);
    }

    @PostMapping
    public Ship createShip(@RequestBody Ship ship) {
        return shipService.saveShip(ship);
    }

    //Get ships by pageable
    @GetMapping("/ships")
    public Page<Ship> getAllShips(Pageable pageable) {
        return shipService.getAllShips(pageable);
    }

    //Get ships by name
    @GetMapping("/search")
    public List<Ship> getShipsByName(@RequestParam String name) {
        return shipService.getShipsByName(name);
    }

    //Update a ship
    @PutMapping("/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable Long id, @RequestBody Ship shipDetails) {
        Ship updatedShip = shipService.updateShip(id, shipDetails);
        return ResponseEntity.ok(updatedShip);
    }

    //delete a ship
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteShip(@PathVariable Long id) {
        shipService.deleteShip(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
