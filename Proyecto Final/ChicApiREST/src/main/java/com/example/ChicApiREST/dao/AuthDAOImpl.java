package com.example.ChicApiREST.dao;

import com.example.ChicApiREST.entities.Appointment;
import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.ProviderRepository;
import com.example.ChicApiREST.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public class AuthDAOImpl implements AuthDAO {

    private Session session; // Hibernate

    private UserRepository userRepository;

    private ProviderRepository providerRepository;

    private User userEnt;

    private Boolean userExist;

    private Provider providerEnt;

    private Boolean providerExist;

    public AuthDAOImpl(Session session, UserRepository userRepository, ProviderRepository providerRepository) {
        this.session = session;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
    }

    public User authenticateUser(String user, String password) {

        userExist = userRepository.existsById(user);

        if (userExist){
            Query<User> query = session.createQuery("FROM User WHERE user = :user AND password = :password", User.class);
            query.setParameter("user", user);
            query.setParameter("password", password);

            userEnt = query.uniqueResult();

            return userEnt;
        } else {
            return null;
        }

    }

    @Override
    public Provider authenticateProvider(String providerUser, String password) {

        providerExist = providerRepository.existsById(providerUser);

        if (providerExist){
            Query<Provider> query = session.createQuery("FROM Provider WHERE providerUser = :providerUser AND password = :password", Provider.class);
            query.setParameter("providerUser", providerUser);
            query.setParameter("password", password);

            providerEnt = query.uniqueResult();

            return providerEnt;
        } else {
            return null;
        }
    }
}
