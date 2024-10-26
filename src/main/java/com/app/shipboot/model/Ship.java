package com.app.shipboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private double speed;
    private double fuelCapacity;
    private double cargoCapacity;

    // Empty Constructor
    public Ship() {
    }

    // Constructor with parameters
    public Ship(String name, double speed, double fuelCapacity, double cargoCapacity) {
        this.name = name;
        this.speed = speed;
        this.fuelCapacity = fuelCapacity;
        this.cargoCapacity = cargoCapacity;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                ", fuelCapacity=" + fuelCapacity +
                ", cargoCapacity=" + cargoCapacity +
                '}';
    }
}
