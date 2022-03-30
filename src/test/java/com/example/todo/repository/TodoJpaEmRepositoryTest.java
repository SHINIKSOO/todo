package com.example.todo.repository;

import com.example.todo.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class TodoJpaEmRepositoryTest {

    private static final String USER_NAME ="temp";

    @Autowired EntityManager em;
    @Autowired TodoRepository repository;


    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    @Rollback(value = false)
    void save() {
        log.info(">>>>> repository: {}", repository);

        //given
        Todo todo = Todo.builder()
                .userId(USER_NAME) //메소드체인
                .title("자바 공부하기")
                .done(false)
                .build();


        //when
        repository.save(todo);
        em.flush();

        //then
    }

    @Disabled
    @Test
    void delete() {
    }
    @Disabled
    @Test
    void deleteById() {
    }
}