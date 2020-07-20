package com.dasc.petclinic.bootstrap;

import com.dasc.petclinic.model.*;
import com.dasc.petclinic.services.OwnerService;
import com.dasc.petclinic.services.PetTypeService;
import com.dasc.petclinic.services.SpecialityService;
import com.dasc.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("DOg");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Owner owner1 = new Owner();
        owner1.setFirstName("David");
        owner1.setLastName("Sanchez");
        owner1.setAddress("Habich");
        owner1.setCity("Huaraz");

        Pet pet1 = new Pet();
        pet1.setName("Otto");
        pet1.setBirthDate(LocalDate.now());
        pet1.setOwner(owner1);
        pet1.setPetType(savedDogPetType);
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Katy");
        owner2.setLastName("Milla");
        owner2.setAddress("Habich");
        owner2.setCity("Huaraz");

        Pet pet2 = new Pet();
        pet2.setName("Guido");
        pet2.setBirthDate(LocalDate.now());
        pet2.setOwner(owner2);
        pet2.setPetType(savedCatPetType);
        owner2.getPets().add(pet2);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Arnold");
        vet1.setLastName("Norabuena");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Lenin");
        vet2.setLastName("Moreno");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
