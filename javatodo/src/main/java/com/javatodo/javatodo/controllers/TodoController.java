package com.javatodo.javatodo.controllers;

import com.javatodo.javatodo.Services.TodoService;
import com.javatodo.javatodo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/todos")
public class TodoController
{
    @Autowired
    private TodoService todoService;

    //PUT http://localhost:2019/todos/todoid/{todoid}
    @PutMapping(value= "todos/todoid/{todoid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo updateRes, @PathVariable long todoid)
    {
        todoService.update(updateRes, todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
