package com.javatodo.javatodo.controllers;

import com.javatodo.javatodo.Services.UserService;
import com.javatodo.javatodo.model.Todo;
import com.javatodo.javatodo.model.User;
import com.javatodo.javatodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userrepos;

    //GET: localhost:2019/users/mine
    @GetMapping(value = "/users/mine", produces = {"application/json"})
    public ResponseEntity<?> getUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = userrepos.findByUsername(authentication.getName());
        long userId = u.getUserid();
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    //POST: localhost:2019/users
    @PostMapping(value = "users", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newUser) throws URISyntaxException
    {
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //POST: localhost:2019/users/todos/{userid}
    @PostMapping(value = "users/todos/{userid}",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addUserTodo(@PathVariable long userid,
                                         @Valid
                                         @RequestBody
                                                 Todo newTodo)
    {
        userService.addTodo(newTodo, userid);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    //DELETE localhost:2019/users/userid/{userid}
    @DeleteMapping("users/userid/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
