package com.example.ChicApiREST;

import com.example.ChicApiREST.entities.*;
import com.example.ChicApiREST.repositories.AppointmentRepository;
import com.example.ChicApiREST.dao.AppointmentDAOImpl;
import com.example.ChicApiREST.repositories.ProviderRepository;
import com.example.ChicApiREST.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ChicApiRestApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ChicApiRestApplication.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        ProviderRepository providerRepository = context.getBean(ProviderRepository.class);
        AppointmentRepository appointmentRepository = context.getBean(AppointmentRepository.class);
        AppointmentDAOImpl appointmentRepositoryImpl = context.getBean(AppointmentDAOImpl.class);


        //crear usuario
        User usuario1 = new User(
                "usuario1",
                "123456",
                "usuario1@email.com",
                "60123456",
                "jose",
                new Address(null, "calle1", "sestao", Province.Bizkaia, "48910", Country.España));

        User usuario2 = new User(
                "usuario2",
                "789012",
                "usuario2@email.com",
                "69876543",
                "manuel",
                new Address(null, "calle2", "barakaldo", Province.Bizkaia, "48920", Country.España));

        User usuario3 = new User(
                "usuario3",
                "789016",
                "usuario3@email.com",
                "69876570",
                "Alejandro",
                new Address(null, "calle2", "barakaldo", Province.Bizkaia, "48920", Country.España));

        //almacenar usuarios
        userRepository.save(usuario1);
        userRepository.save(usuario2);
        userRepository.save(usuario3);

        //crear tipo de negocio para el proveedor 1
        Set<BusinessType> businessType = new HashSet<>();
        businessType.add(BusinessType.Barbershop);


        //crear provedores y servicios
        Provider proveedor1 = new Provider(
                "proveedor1",
                "245867P",
                "proveedor1@email.com",
                "9123456",
                "Barbería Carmelo",
                new Address(null, "la linea", "Puerto La Cruz", Province.Bizkaia, "48910", Country.España),
                new Image(null, "http://10.0.2.2:8085/images/peluqueria1.jpg"),
                businessType
        );

        Service service1 = new Service(null, "Corte de caballero", 8f, Genre.male);
        Service service2 = new Service(null, "Corte de damas", 9f, Genre.female);

        proveedor1.getServices().add(service1);
        proveedor1.getServices().add(service2);

        providerRepository.save(proveedor1);

        //crear tipo de negocio para el proveedor 1
        Set<BusinessType> businessType2 = new HashSet<>();
        businessType2.add(BusinessType.Barbershop);
        businessType2.add(BusinessType.Hair_Salon);

        Provider proveedor2 = new Provider(
                "proveedor2",
                "478213P",
                "proveedor2@email.com",
                "9234615",
                "Cesar's peluquería",
                new Address(null, "Alberto Arrue #7", "Sestao", Province.Bizkaia, "48910", Country.España),
                new Image(null, "http://10.0.2.2:8085/images/peluqueria2.jpg"),
                businessType2
        );

        Service service3 = new Service(null, "Corte de caballero", 8f, Genre.male);
        Service service4 = new Service(null, "Corte de damas", 9f, Genre.female);
        Service service5 = new Service(null, "Corte de niños y niñas", 7f, Genre.unisex);
        Service service6 = new Service(null, "Corte de jubilados", 7f, Genre.male);
        Service service7 = new Service(null, "Lavado del cabello", 3f, Genre.female);


        proveedor2.getServices().add(service3);
        proveedor2.getServices().add(service4);
        proveedor2.getServices().add(service5);
        proveedor2.getServices().add(service6);
        proveedor2.getServices().add(service7);

        providerRepository.save(proveedor2);

        Appointment cita1 = new Appointment(
                null,
                LocalDateTime.of(2024, 01, 20, 10, 30),
                30L,
                usuario1,
                proveedor2
        );

        cita1.getServices().add(service3);

        appointmentRepository.save(cita1);


        Appointment cita2 = new Appointment(
                null,
                LocalDateTime.of(2024, 02, 15, 15,0),
                50L,
                usuario2,
                proveedor2
        );

        cita2.getServices().add(service4);

        appointmentRepository.save(cita2);


        Appointment cita3 = new Appointment(
                null,
                LocalDateTime.of(2024, 03, 7, 9, 15),
                25L,
                usuario1,
                proveedor1
        );

        cita3.getServices().add(service1);

        appointmentRepository.save(cita3);

    }

}

