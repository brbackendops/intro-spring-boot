package net.javaguides.springboot_api.controllers;

import net.javaguides.springboot_api.dto.UserDto;
import net.javaguides.springboot_api.entity.User;
import net.javaguides.springboot_api.exception.ErrorDetails;
import net.javaguides.springboot_api.exception.ResourceNotFoundException;
import net.javaguides.springboot_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userServ;


    @GetMapping("/")
    public List<User> getAllUsers(){
        return userServ.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) throws ResourceNotFoundException {
         User user = userServ.findOneuser(id);
         if ( user == null ) {
            throw new ResourceNotFoundException("user with this id is not found");
         }
        return new ResponseEntity<>(user,HttpStatus.OK);
    // ResponseEntity.status(HttpStatus.OK)
    //                .body(Map.of(
    //                        "data", user
    //                ));
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody UserDto body) {
        try {
            userServ.createUser(body);
            return new ResponseEntity<>("user created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            String error_msg = e.toString();
            return new ResponseEntity<>(error_msg, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long id , @RequestBody User body) {
        try {
            userServ.updateUser(body,id);
            return new ResponseEntity<>("user updated successfully", HttpStatus.OK);
        } catch (Exception e){
            String err_msg = e.toString();
            return new ResponseEntity<>(err_msg, HttpStatus.BAD_REQUEST);
        }
    }

}
