package com.example.ChicApiREST.dao;

import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.entities.User;

import java.util.List;

public interface AppointmentDAO {
    public List<Appointment> findByUserEager(String user);

    public Boolean existsByUser(String user);

    public List<Appointment> findByProviderEager(String provider);

    public Boolean existsByProvider(String provider);

}
