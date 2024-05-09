package com.example.ChicApiREST.services;

import com.example.ChicApiREST.ChicApiRestApplication;
import com.example.ChicApiREST.dao.AppointmentDAO;
import com.example.ChicApiREST.dao.AppointmentDAOImpl;
import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.AppointmentRepository;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private AppointmentRepository appointmentRepository;
    private AppointmentDAO appointmentDAO;


    public UserService(AppointmentRepository appointmentRepository, AppointmentDAO appointmentDAO) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDAO = appointmentDAO;
    }

    //Borro las citas del usuario
    public void deleteUserAppointment(String user) {

        List<Appointment> result = new ArrayList<>();

        // Obtengo las citas por el usuario
        if (appointmentDAO.existsByUser(user)) {
            result = appointmentDAO.findByUserEager(user);

            //Borro las citas que posee el usuario
            appointmentRepository.deleteAll(result);
        }

    }
}
