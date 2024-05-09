package com.example.ChicApiREST.dao;

import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.entities.User;

import java.util.Optional;

public interface AuthDAO {

    User authenticateUser(String user, String password);

    Provider authenticateProvider(String providerUser, String password);
}
