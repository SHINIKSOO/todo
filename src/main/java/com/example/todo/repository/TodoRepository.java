package com.example.todo.repository;

import com.example.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    List<Todo> findAll();

    Optional<Todo> findById(Integer id);

    List<Todo> findByUserId(String userId);

    void save(Todo todo);

    void delete(Todo todo); //JPA

    void deleteById(Integer id);
}
