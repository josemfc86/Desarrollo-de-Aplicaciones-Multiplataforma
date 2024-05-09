package com.example.ChicApiREST.services;

import com.example.ChicApiREST.dao.AppointmentDAO;
import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    private AppointmentRepository appointmentRepository;
    private AppointmentDAO appointmentDAO;


    public ProviderService(AppointmentRepository appointmentRepository, AppointmentDAO appointmentDAO) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDAO = appointmentDAO;
    }

    //Borro las citas del proveedor
    public void deleteProviderAppointment(String user) {

        List<Appointment> result = new ArrayList<>();

        // Obtengo las citas por el proveedor
        if (appointmentDAO.existsByUser(user)) {
            result = appointmentDAO.findByUserEager(user);

            //Borro las citas que posee el usuario
            appointmentRepository.deleteAll(result);
        }

    }
}
