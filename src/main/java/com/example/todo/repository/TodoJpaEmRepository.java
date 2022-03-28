package com.example.todo.repository;

import com.example.todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoJpaEmRepository implements TodoRepository{

    //@PersistenceContext

    //DI
    //1. 생성자주입
    //2. Setter 주입
    //3. 필드주입 - 테스트코드만들기가어렵다? 스프링이없으면 주입하기어려웡!


    private final EntityManager em;




    @Override
    public List<Todo> findAll() {
        String jpql = "select t from Todo t"; //JPQL(Java Persistence Query Language)
        return em.createQuery(jpql, Todo.class)
                .getResultList();

    }
}
