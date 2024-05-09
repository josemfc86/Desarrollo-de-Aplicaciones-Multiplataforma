package com.example.ChicApiREST.controllers;

import com.example.ChicApiREST.dao.AppointmentDAO;
import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.repositories.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {
    private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);

    private AppointmentRepository appointmentRepository;
    private AppointmentDAO appointmentDAO;


    public AppointmentController(AppointmentRepository appointmentRepository, AppointmentDAO appointmentDAO) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDAO = appointmentDAO;
    }

    //CRUD sobre la entidad Appointment
    //Buscar todas los citas
    @GetMapping("/api/appointments")
    public ResponseEntity<List<Appointment>> findAll() {
        if (!appointmentRepository.findAll().isEmpty()) {
            //recupera y devuelve los usuarios de la base de datos
            List<Appointment> appointments= appointmentRepository.findAll();
            return ResponseEntity.ok(appointments);
        }
        else
            return ResponseEntity.notFound().build();
    }

    //Buscar todas las citas de un usuario
    @GetMapping("/api/appointments/users/{user}")
    public ResponseEntity<List<Appointment>> findAllUser(@PathVariable String user) {
        //Busca si existen citas del usuario
        if (appointmentDAO.existsByUser(user)) {
            //recupera y devuelve las citas de la base de datos
            List<Appointment> appointments = appointmentDAO.findByUserEager(user);
            return ResponseEntity.ok(appointments);
        }
        else
            return ResponseEntity.notFound().build();
    }

    //Buscar todas las citas de un proveedor
    @GetMapping("/api/appointments/providers/{provider}")
    public ResponseEntity<List<Appointment>> findAllProvider(@PathVariable String provider) {

        //Busca si existen citas para el proveedor
        if (appointmentDAO.existsByProvider(provider)) {
            //recupera y devuelve las citas de la base de datos
            List<Appointment> appointments = appointmentDAO.findByProviderEager(provider);
            return ResponseEntity.ok(appointments);
        }
        else
            return ResponseEntity.notFound().build();

    }
    //Crear una nueva cita en base de datos
    @PostMapping("/api/appointments")
    public ResponseEntity<Void> create(@RequestBody Appointment appointment) {

        /*// Guardar la cita recibida por parámetro en la base de datos
        Boolean appointmentOpt = appointmentRepository.existsById(appointment.getId());

        if (appointmentOpt) {//Quiere decir que existe la cita y por lo tanto no es una creacion
            log.warn("Trying to create a appointment that already exists in the DB");
            System.out.println("Trying to create a appointment that already exists in the DB");
            return ResponseEntity.badRequest().build();
        }*/
        Appointment result = appointmentRepository.save(appointment);
        return ResponseEntity.ok().build();

    }

    //Actualizar una cita existente en base de datos
    @PutMapping("/api/appointments")
    public ResponseEntity<Appointment> update(@RequestBody Appointment appointment) {

        if (!appointmentRepository.existsById(appointment.getId())) {//Si no tiene id quiere decir que es una creacion no una actualizacion
            log.warn("Trying to update a non existent appointment");
            return ResponseEntity.notFound().build();
        }

        //El proceso de actualizacion
        Appointment result = appointmentRepository.save(appointment);
        return ResponseEntity.ok(result);
    }

/*
    //Borrar una cita de la base de datos
    @DeleteMapping("/api/appointments/{appointment}")
    public ResponseEntity<Appointment> delete(@PathVariable String appointment) {

        if (!appointmentRepository.existsById(appointment)) {
            log.warn("Trying to delete a non existent appointment");
            return ResponseEntity.notFound().build();
        }

        appointmentRepository.deleteById(appointment);
        return ResponseEntity.noContent().build();
    }


    //Borrar todas las citas de la base de datos
    @DeleteMapping("/api/appointments")
    public ResponseEntity<Appointment> deleteAll() {
        log.info("REST Request for delete all appointments");
        appointmentRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }*/
}
