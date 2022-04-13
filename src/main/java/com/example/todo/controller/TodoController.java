package com.example.todo.controller;


import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//SPRING 3LAYER @Controller @Service @Repository(DAO)


@RestController
@RequiredArgsConstructor
public class TodoController {

    private  static final String TEMP_USER_ID = "temp";
    private final TodoService service;

    // API 요소 : HTTP 요청 메소드 + URI PATH
    @GetMapping("/todo")
    public ResponseEntity<?> getTodoList() {
        List<Todo> todos = service.getList(TEMP_USER_ID);

        return ResponseEntity.ok().body(todos);

    }

    @PostMapping("/todo")
    public ResponseEntity<?> createTodo(String title) {
        Todo todo = Todo.builder()
                .userId(TEMP_USER_ID)
                        .title(title)
                        .build();
        service.create(todo);
        return getTodoList();

    }

    @PutMapping("/todo")
    public ResponseEntity<?> updateTodo(Integer id, String title, boolean done) {
        Todo todo = Todo.builder()
                .id(id)
                .userId(TEMP_USER_ID)
                .title(title)
                .done(done)
                .build();

        service.update(todo);
        return getTodoList();


    }

    @DeleteMapping("/todo")
    public ResponseEntity<?> deleteTodo(Integer id) {

        Todo todo = Todo.builder()
                .userId(TEMP_USER_ID)
                .id(id)
                .build();
        service.delete(todo);
        return getTodoList();
    }

}
