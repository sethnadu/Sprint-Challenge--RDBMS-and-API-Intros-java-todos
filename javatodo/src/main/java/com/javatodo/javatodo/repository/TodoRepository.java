package com.javatodo.javatodo.repository;

import com.javatodo.javatodo.model.Todo;
import com.javatodo.javatodo.view.UserTodo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface TodoRepository extends CrudRepository<Todo, Long>
{

    @Transactional
    @Modifying
    @Query(value = "SELECT u.username as user, count(t.todoid) as todo FROM todo t JOIN users u on t.userid = u.userid GROUP BY u.username", nativeQuery = true)
    ArrayList<UserTodo> addUserTodos();
}
