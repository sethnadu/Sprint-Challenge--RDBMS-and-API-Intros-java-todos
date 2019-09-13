package com.javatodo.javatodo.Services;

import com.javatodo.javatodo.model.Todo;
import com.javatodo.javatodo.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todoRepos;

    @Transactional
    @Override
    public Todo update(Todo todo, long todoid)
    {
        Todo currentTodo = todoRepos.findById(todoid)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(todoid)));
        if (todo.getDescription() != null)
        {
            currentTodo.setDescription(todo.getDescription());
        }

        return todoRepos.save(currentTodo);
    }
}
