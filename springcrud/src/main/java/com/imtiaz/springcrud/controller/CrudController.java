package com.imtiaz.springcrud.controller;

import com.imtiaz.springcrud.entity.User;
import com.imtiaz.springcrud.model.MessageResponse;
import com.imtiaz.springcrud.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/vi")
public class CrudController {


    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody User user)
    {
        System.out.println(user.name);
        try {
            CrudService.addUser(user);
        } catch (IOException e){
            System.out.println(e);
        }
        return new ResponseEntity<Object>(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/read")
    public ResponseEntity<?> read(@RequestParam("id") int id)
    {
        User user = new User(1,"imtiaz", "mail@mail.com");
        User result;
        try{
            result = CrudService.getUserById(id);
        } catch (IOException e){
            result = null;
            System.out.println(e);
        }
        return new ResponseEntity<Object>(result, HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody User user)
    {
        System.out.println("update => " + user.name);
        String message;
        try{
            message = CrudService.updateUser(user);
        } catch (IOException e){
            message = "Some error occured!";
            System.out.println(e);
        }
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(message);
        return new ResponseEntity<Object>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?>  delete(@RequestParam("id") int id)
    {
        System.out.println("delete => " + id);
        String message;
        try{
            message = CrudService.deleteUser(id);
        } catch (IOException e){
            message = "Some error occured!";
            System.out.println(e);
        }
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(message);
        return new ResponseEntity<Object>(messageResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/health")
    public String health()
    {
        return "Fresh!";
    }

}
