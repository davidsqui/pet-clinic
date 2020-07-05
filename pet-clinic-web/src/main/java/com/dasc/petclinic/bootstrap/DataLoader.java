package com.dasc.petclinic.bootstrap;

import com.dasc.petclinic.model.Owner;
import com.dasc.petclinic.model.Vet;
import com.dasc.petclinic.services.OwnerService;
import com.dasc.petclinic.services.VetService;
import com.dasc.petclinic.services.map.OwnerServiceMap;
import com.dasc.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("David");
        owner1.setLastName("Sanchez");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Katy");
        owner2.setLastName("Milla");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Arnold");
        vet1.setLastName("Norabuena");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Lenin");
        vet2.setLastName("Moreno");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");

    }
}