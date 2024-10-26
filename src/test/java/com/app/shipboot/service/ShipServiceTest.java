package com.app.shipboot.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.app.shipboot.exception.ResourceNotFoundException;
import com.app.shipboot.model.Ship;
import com.app.shipboot.repository.ShipRepository;

public class ShipServiceTest {

    // simulate repository
    @Mock
    private ShipRepository shipRepository;

    //inject mock
    @InjectMocks
    private ShipService shipService;

    //Initialize the mocks and prepare the test environment
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //TESTs:
    //Test for get ship by id
    @Test
    public void testGetShipById() {
        // Create a mock Ship object
        Ship ship = new Ship("Voyager", 25.5, 1000.0, 500.0);
        ship.setId(1L);

        // Defines the behavior of the mock
        when(shipRepository.findById(1L)).thenReturn(Optional.of(ship));

        // Call the method
        Ship foundShip = shipService.getShipById(1L);

        // Verify that the Ship found is the same as the simulated one
        assertEquals(ship.getName(), foundShip.getName());
        assertEquals(ship.getSpeed(), foundShip.getSpeed());
        assertEquals(ship.getFuelCapacity(), foundShip.getFuelCapacity());
        assertEquals(ship.getCargoCapacity(), foundShip.getCargoCapacity());
    }

    //Test for get ship by name
    @Test
    public void testGetShipByName() {
        Ship ship = new Ship("Voyager", 25.5, 1000.0, 500.0);
        ship.setId(1L);
        String name = "oya";
    
        when(shipRepository.findByNameContaining(name)).thenReturn(List.of(ship));
    
        List<Ship> foundShips = shipService.getShipsByName(name);
    
        assertEquals(1, foundShips.size());
        Ship foundShip = foundShips.get(0);
        
        assertEquals(ship.getName(), foundShip.getName());
        assertEquals(ship.getSpeed(), foundShip.getSpeed());
        assertEquals(ship.getFuelCapacity(), foundShip.getFuelCapacity());
        assertEquals(ship.getCargoCapacity(), foundShip.getCargoCapacity());
    }

    //Test for get all ships
    @Test
    public void testGetAllShips() {
        Ship ship1 = new Ship("Voyager", 25.5, 1000.0, 500.0);
        Ship ship2 = new Ship("Enterprise", 30.0, 1200.0, 700.0);
        List<Ship> ships = Arrays.asList(ship1, ship2);

        Pageable pageable = PageRequest.of(0, 2);
        Page<Ship> page = new PageImpl<>(ships, pageable, ships.size());

        when(shipRepository.findAll(pageable)).thenReturn(page);

        Page<Ship> result = shipService.getAllShips(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals("Voyager", result.getContent().get(0).getName());
        assertEquals("Enterprise", result.getContent().get(1).getName());
    }

    //Test for save a ship
    @Test
    public void testSaveShip() {
        Ship ship = new Ship("Voyager", 25.5, 1000.0, 500.0);
        
        when(shipRepository.save(ship)).thenReturn(ship);

        Ship savedShip = shipService.saveShip(ship);

        assertNotNull(savedShip);
        assertEquals(ship.getName(), savedShip.getName());
        assertEquals(ship.getSpeed(), savedShip.getSpeed());
        assertEquals(ship.getFuelCapacity(), savedShip.getFuelCapacity());
        assertEquals(ship.getCargoCapacity(), savedShip.getCargoCapacity());

        verify(shipRepository, times(1)).save(ship);
    }

    //Test for update a ship
    @Test
    public void testUpdateShip() {
        Ship existingShip = new Ship("Voyager", 25.5, 1000.0, 500.0);
        existingShip.setId(1L);
    
        when(shipRepository.findById(existingShip.getId())).thenReturn(Optional.of(existingShip));
    
        existingShip.setName("Voyager X");
        existingShip.setSpeed(30.0);
        existingShip.setFuelCapacity(1200.0);
        existingShip.setCargoCapacity(600.0);
    
        when(shipRepository.save(existingShip)).thenReturn(existingShip);
    
        Ship resultShip = shipService.updateShip(existingShip.getId(), existingShip);
    
        assertNotNull(resultShip);
        assertEquals("Voyager X", resultShip.getName());
        assertEquals(30.0, resultShip.getSpeed());
        assertEquals(1200.0, resultShip.getFuelCapacity());
        assertEquals(600.0, resultShip.getCargoCapacity());
    
        // Verificar que el repositorio fue llamado correctamente
        verify(shipRepository, times(1)).findById(existingShip.getId());
        verify(shipRepository, times(1)).save(existingShip);
    }
    
    //Test for delete a ship
    @Test
    public void testDeleteShip() {
        Long shipId = 1L;
        Ship ship = new Ship("Voyager", 25.5, 1000.0, 500.0);
        ship.setId(shipId);
    
        when(shipRepository.findById(shipId)).thenReturn(Optional.of(ship));
    
        doNothing().when(shipRepository).deleteById(shipId);
        shipService.deleteShip(shipId);
        verify(shipRepository, times(1)).deleteById(shipId);
    }

    //Test for delete a ship with exception
    @Test
    public void testDeleteShipNotFound() {
        Long shipId = 1L;

        doThrow(new ResourceNotFoundException("Ship not found with id " + shipId)).when(shipRepository).deleteById(shipId);

        assertThrows(ResourceNotFoundException.class, () -> shipService.deleteShip(shipId));
    }
}
