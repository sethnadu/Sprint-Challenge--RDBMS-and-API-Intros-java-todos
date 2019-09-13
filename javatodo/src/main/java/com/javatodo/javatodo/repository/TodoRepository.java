package com.javatodo.javatodo.repository;

import com.javatodo.javatodo.model.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepository extends CrudRepository<Todo, Long>
{

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO todos(todoid, userid) values (:todoid, :userid)", nativeQuery = true)
    void addUserTodos(long todoid, long userid);
}
