package com.example.todo.repository;

import com.example.todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Todo> findById(Integer id) {
        String jpql = "select t from Todo t where t.id = :id"; //JPQL(Java Persistence Query Language)

        try {
            Todo todo = em.createQuery(jpql, Todo.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(todo);

        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Todo> findByUserId(String userId) {
        String jpql = "select t from Todo t where t.userId = :userId"; //JPQL(Java Persistence Query Language)


           return  em.createQuery(jpql, Todo.class)
                    .setParameter("userId", userId)
                    .getResultList();

    }

    @Override

    public void save(Todo todo) {
        em.persist(todo);
    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
