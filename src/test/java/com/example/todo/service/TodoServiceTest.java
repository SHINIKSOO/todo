package com.example.todo.service;


import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional //기본설정이 Rollback
class TodoServiceTest {

    private static final String USER_NAME = "temp";

    @Autowired
    TodoService service;
    @Autowired
    TodoRepository repository;


    @Test
    void getList(){
        //Given

        int createCount = 3;
        createTodoList(createCount);
        List<Todo> result = service.getList((USER_NAME));
        assertThat(result).hasSize(createCount);
    }

    private void createTodoList(int createCount) {
        for(int i =0; i< createCount; i ++) {
            Todo todo = Todo.builder()
                    .userId(USER_NAME)
                    .title("자바 공부하기" + (i+1))
                    .done(false)
                    .build();
            service.create(todo);
        }
    }





    @Test
    void create() {
        //Given
        Todo todo = Todo.builder()
                .userId(USER_NAME)
                .title("자바 공부하기")
                .done(false)
                .build();
        //When
        System.out.println("========================1");
        service.create(todo);
        System.out.println("=========================2");

        //Then
        Optional<Todo> result = repository.findById(todo.getId());
        System.out.println("=========================3");
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isPositive();
        assertThat(result.get().getTitle()).isEqualTo(todo.getTitle());
    }


    @Test
    void create_invalidEntity() {
        //todo가 null인 경우
    }

    @Test
    void create_withoutUserId() {
        //ID가 null인 경우
    }



    @Test
    void update() {
        int createCount = 3;
        createTodoList(createCount);
        List<Todo> result = service.getList(USER_NAME);
        Todo updatingTodo = result.get(1);
        updatingTodo.setTitle("스프링 공부하기");
        updatingTodo.setDone(true);




        service.update(updatingTodo);


        Optional<Todo> updatedTodo = repository.findById(updatingTodo.getId());
        assertThat(updatedTodo).isPresent();
        assertThat(updatedTodo.get().getTitle()).isEqualTo(updatingTodo.getTitle());
        assertThat(updatedTodo.get().isDone()).isEqualTo(updatingTodo.isDone());

    }

    @Test
    void delete(){

    }



}
