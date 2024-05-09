package com.example.ChicApiREST.controllers;


import com.example.ChicApiREST.entities.Service;
import com.example.ChicApiREST.repositories.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ServiceController {
    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    private ServiceRepository serviceRepository;



    public ServiceController(ServiceRepository serviceRepository) {

        this.serviceRepository = serviceRepository;
    }

    //Buscar un solo servicio en base de datos segun su primary key
    @GetMapping("/api/services/{service}")
    public ResponseEntity<Service> findOneByPK(@PathVariable Long service) {

        Optional<Service> serviceOpt = serviceRepository.findById(service);

        if (serviceOpt.isPresent()) {
            return ResponseEntity.ok(serviceOpt.get());
        } else
            return ResponseEntity.notFound().build();

    }

}
