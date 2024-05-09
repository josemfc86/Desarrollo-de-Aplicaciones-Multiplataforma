package com.example.ChicApiREST.dao;

import com.example.ChicApiREST.ChicApiRestApplication;
import com.example.ChicApiREST.config.JpaConfig;
import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.ProviderRepository;
import com.example.ChicApiREST.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

    private Session session; // Hibernate

    private UserRepository userRepository;

    private ProviderRepository providerRepository;

    private User userEnt;

    private Provider providerEnt;

    public AppointmentDAOImpl(Session session, UserRepository userRepository, ProviderRepository providerRepository) {
        this.session = session;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
    }


    public List<Appointment> findByUserEager(String user) {

        userEnt = userRepository.getReferenceById(user);

        Query<Appointment> query = session.createQuery("from Appointment where user = :user", Appointment.class);
        query.setParameter("user", userEnt);

//        Query<Appointment> query = session.createQuery("SELECT a FROM Appointment a JOIN FETCH a.user WHERE a.user = :user", Appointment.class);
//        query.setParameter("user", userEnt);

        List<Appointment> appointments = query.list();

        return appointments;
    }


    public Boolean existsByUser(String user) {

        userEnt = userRepository.getReferenceById(user);

        Query<Appointment> query = session.createQuery("from Appointment where user = :user", Appointment.class);
        query.setParameter("user", userEnt);

//        Query<Appointment> query = session.createQuery("SELECT a FROM Appointment a JOIN FETCH a.user WHERE a.user = :user", Appointment.class);
//        query.setParameter("user", userEnt);

        if (query.list().isEmpty()) {

            return false;
        } else {

            return true;
        }

    }

    public List<Appointment> findByProviderEager(String provider) {

        providerEnt = providerRepository.getReferenceById(provider);

        Query<Appointment> query = session.createQuery("SELECT a FROM Appointment a JOIN FETCH a.provider WHERE a.provider = :provider", Appointment.class);
        query.setParameter("provider", providerEnt);

        List<Appointment> appointments = query.list();

        return appointments;
    }


    public Boolean existsByProvider(String provider) {

        providerEnt = providerRepository.getReferenceById(provider);

        Query<Appointment> query = session.createQuery("SELECT a FROM Appointment a JOIN FETCH a.provider WHERE a.provider = :provider", Appointment.class);
        query.setParameter("provider", providerEnt);

        if (query.list().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

}
