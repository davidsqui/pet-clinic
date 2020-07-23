package com.dasc.petclinic.services.map;

import com.dasc.petclinic.model.Owner;
import com.dasc.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    OwnerService ownerService;
    final Long ownerId = 1l;
    final String lastName = "sanchez";

    @BeforeEach
    void setUp() {
        ownerService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerService.findAll();
        assertEquals(ownerId, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2l;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerService.save(owner);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner owner = ownerService.save(Owner.builder().build());

        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void delete() {
        ownerService.delete(ownerService.findById(ownerId));

        assertEquals(0, ownerService.findAll().size());
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByLastName() {
        Owner owner = ownerService.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }
}