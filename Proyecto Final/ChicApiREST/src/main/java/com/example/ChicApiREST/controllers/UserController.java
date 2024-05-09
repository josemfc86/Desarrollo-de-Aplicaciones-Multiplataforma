package com.example.ChicApiREST.controllers;

import com.example.ChicApiREST.entities.User;
import com.example.ChicApiREST.repositories.UserRepository;
import com.example.ChicApiREST.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;

    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService){

        this.userRepository = userRepository;
        this.userService = userService;
    }

    //CRUD sobre la entidad User

    //Obtener todos los usuarios
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> findAll(){
        if (!userRepository.findAll().isEmpty()) {
            //recupera y devuelve los usuarios de la base de datos
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok(users);
        }
        else
            return ResponseEntity.notFound().build();

    }

    //Obtener un solo usuario en base de datos segun su primary key
    @GetMapping("/api/users/{user}")
    public ResponseEntity<User> findOneByPK(@PathVariable String user){

        Optional<User> userOpt = userRepository.findById(user);

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        else
            return ResponseEntity.notFound().build();

    }

    //Buscar si existe un usuario
    @GetMapping("/api/users/check/{user}")
    public Boolean checkUserExist(@PathVariable String user){

        return userRepository.existsById(user);

    }


    //Crear un nuevo usuario en base de datos
    @PostMapping("/api/users")
    public ResponseEntity <User> create(@RequestBody User user){

        // Guardar el usuario recibido por parámetro en la base de datos
        Optional<User> userOpt = userRepository.findById(user.getUser());

        if (userOpt.isPresent()) {//Quiere decir que existe el usuario y por lo tanto no es una creacion
            log.warn("Trying to create a user that already exists in the DB");
            System.out.println("Trying to create a user that already exists in the DB");
            return ResponseEntity.badRequest().build();
        }
        User result = userRepository.save(user);
        return ResponseEntity.ok(result);

    }

    //Actualizar un usuario existente en base de datos
    @PutMapping("/api/users")
    public ResponseEntity <User> update(@RequestBody User user){

        if (!userRepository.existsById(user.getUser())) {//Si no tiene id quiere decir que es una creacion no una actualizacion
            log.warn("Trying to update a non existent User");
            return ResponseEntity.notFound().build();
        }

        //El proceso de actualizacion
        User result = userRepository.save(user);
        return ResponseEntity.ok(result);
    }


    //Borrar un usuario de la base de datos
    @DeleteMapping("/api/users/{user}")
    public ResponseEntity<User> delete(@PathVariable String user){

        if (!userRepository.existsById(user)) {
            log.warn("Trying to delete a non existent user");
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(user);
        return ResponseEntity.noContent().build();
    }


    //Borrar todos los usuarios de la base de datos
    @DeleteMapping("/api/users")
    public ResponseEntity<User> deleteAll(){
        log.info("REST Request for delete all users");
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
