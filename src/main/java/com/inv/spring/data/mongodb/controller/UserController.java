package com.inv.spring.data.mongodb.controller;

import com.inv.spring.data.mongodb.model.User;
import com.inv.spring.data.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User newUser = userRepository.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        Optional<User> userData = userRepository.findById(id);

        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setName(user.getName());
            _user.setEmail(user.getEmail());
            _user.setPassword(user.getPassword());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @DeleteMapping("/delete-user/{id}")
    // public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    // try {
    // userRepository.deleteById(id);
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") String id) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<User> existingUser = userRepository.findById(id);

            if (existingUser.isPresent()) {
                userRepository.deleteById(id);
                response.put("message", "Usuario eliminado con éxito");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "No se encontró el usuario con ID: " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("message", "Error al eliminar el usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
