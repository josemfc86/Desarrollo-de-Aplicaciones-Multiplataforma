package com.example.ChicApiREST.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import jakarta.persistence.EntityManagerFactory;

@Configuration
public class JpaConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory; // JPA

    @Bean
    public Session getSession(){
        System.out.println("Creando bean Session");
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        return sessionFactory.openSession(); // Session de Hibernate
    }
}