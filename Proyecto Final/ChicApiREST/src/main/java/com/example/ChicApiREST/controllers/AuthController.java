package com.example.ChicApiREST.controllers;

import com.example.ChicApiREST.dao.AppointmentDAO;
import com.example.ChicApiREST.dao.AuthDAO;
import com.example.ChicApiREST.entities.Provider;
import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private AuthDAO authDAO;

    public AuthController(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    @GetMapping("/api/user/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestParam String user, @RequestParam String password) {
        User authenticatedUser = authDAO.authenticateUser(user, password);
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/provider/authenticate")
    public ResponseEntity<Provider> authenticateProvider(@RequestParam String providerUser, @RequestParam String password) {
        Provider authenticatedProvider = authDAO.authenticateProvider(providerUser, password);
        if (authenticatedProvider != null) {
            return ResponseEntity.ok(authenticatedProvider);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
