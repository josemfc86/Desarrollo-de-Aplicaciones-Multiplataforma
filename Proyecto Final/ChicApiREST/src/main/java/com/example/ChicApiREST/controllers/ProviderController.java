package com.example.ChicApiREST.controllers;


import com.example.ChicApiREST.dao.ProviderDAO;
import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.BusinessType;
import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.ProviderRepository;
import com.example.ChicApiREST.services.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ProviderController {

    private static final Logger log = LoggerFactory.getLogger(ProviderController.class);

    private ProviderRepository providerRepository;

    private ProviderDAO providerDAO;

    private ProviderService providerService;

    public ProviderController(ProviderRepository providerRepository, ProviderDAO providerDAO, ProviderService providerService) {
        this.providerRepository = providerRepository;
        this.providerDAO = providerDAO;
        this.providerService = providerService;
    }

    //CRUD sobre la entidad Provider

    //Buscar todos los proveedores por tipo de negocio
    @GetMapping("/api/providers/businessTypes/{businessType}")
    public ResponseEntity<List<Provider>> findAllByBT(@PathVariable BusinessType businessType) {

        //recupera los proveedores por tipo de negocio de la base de datos
        List<Provider> providers = providerDAO.findByBusinessTypeEager(businessType);

        if (!providers.isEmpty()) {
            //devuelve los proveedores
            return ResponseEntity.ok(providers);
        }
        else
            return ResponseEntity.notFound().build();
    }

    //Buscar un solo proveedor en base de datos segun su primary key
    @GetMapping("/api/providers/{provider}")
    public ResponseEntity<Provider> findOneByPK(@PathVariable String provider) {

        Optional<Provider> providerOpt = providerRepository.findById(provider);

        if (providerOpt.isPresent()) {
            return ResponseEntity.ok(providerOpt.get());
        } else
            return ResponseEntity.notFound().build();

    }

    //Crear un nuevo proveedor en base de datos
    @PostMapping("/api/providers")
    public ResponseEntity<Provider> create(@RequestBody Provider provider) {

        // Guardar el proveedor recibido por parámetro en la base de datos
        Optional<Provider> providerOpt = providerRepository.findById(provider.getProviderUser());

        if (providerOpt.isPresent()) {//Quiere decir que existe el proveedor y por lo tanto no es una creacion
            log.warn("Trying to create a provider that already exists in the DB");
            System.out.println("Trying to create a provider that already exists in the DB");
            return ResponseEntity.badRequest().build();
        }
        Provider result = providerRepository.save(provider);
        return ResponseEntity.ok(result);

    }

    //Actualizar un proveedor existente en base de datos
    @PutMapping("/api/providers")
    public ResponseEntity<Provider> update(@RequestBody Provider provider) {

        if (!providerRepository.existsById(provider.getProviderUser())) {//Si no tiene id quiere decir que es una creacion no una actualizacion
            log.warn("Trying to update a non existent Provider");
            return ResponseEntity.notFound().build();
        }

        //El proceso de actualizacion
        Provider result = providerRepository.save(provider);
        return ResponseEntity.ok(result);
    }


    //Borrar un proveedor de la base de datos
    @DeleteMapping("/api/providers/{provider}")
    public ResponseEntity<Provider> delete(@PathVariable String provider) {

        if (!providerRepository.existsById(provider)) {
            log.warn("Trying to delete a non existent provider");
            return ResponseEntity.notFound().build();
        }

        providerRepository.deleteById(provider);
        return ResponseEntity.noContent().build();
    }


    //Borrar todos los proveedores de la base de datos
    @DeleteMapping("/api/providers")
    public ResponseEntity<Provider> deleteAll() {
        log.info("REST Request for delete all providers");
        providerRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}