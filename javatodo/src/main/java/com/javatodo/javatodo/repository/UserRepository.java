package com.javatodo.javatodo.repository;

import com.javatodo.javatodo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
