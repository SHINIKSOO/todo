package com.example.todo.service;


import com.example.todo.entity.Todo;
import com.example.todo.exception.ApplicationException;
import com.example.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final 붙은 필드가 파라미터인 생성자를 자동 생성
public class TodoService{



    private final TodoRepository repository;

    public List<Todo> getList(String userId) {
        return repository.findByUserId(userId);
    }

    @Transactional
    public void create(Todo todo) {
        validateTodo(todo);
        repository.save(todo);
        log.info("Todo가 등록되었습니다. {}", todo.getId());

    }


    private  void validateTodo(Todo todo) {
        if (todo == null) {
            String msg = "Todo가 null입니다.";
            log.error(msg);
            throw new ApplicationException("Todo가 null입니다");

        }
        if( todo.getUserId() == null) {
            String msg = "Todo의 UserId가 null입니다.";
            log.error(msg);
            throw new ApplicationException("Todo가 null입니다");

        }



    }
    @Transactional
    public  void update(Todo newTodo) {

        log.debug(">>> newTodo: {}", newTodo);
        validateTodo(newTodo);
        Optional<Todo> oldTodo = repository.findById(newTodo.getId());
        log.debug(">>>> oldTodo : {}", oldTodo);

        oldTodo.ifPresentOrElse(
                todo -> {
                    todo.setTitle((newTodo.getTitle()));
                    todo.setDone(newTodo.isDone());
                    //repository.save(todo); 없어두됑?
                    log.info("Todo가 수정되었습니다. {}", todo.getId());
                },
                () -> log.warn("수정할 Todo가 없습니다. {}", newTodo.getId())

        );
    }
    @Transactional
    public void delete(Todo todo) {
        log.debug(">>> todo : {}", todo);
        validateTodo(todo);
        repository.delete(todo);
        log.info("Todo가 삭제되었습니다. {}", todo.getId());
    }
}
