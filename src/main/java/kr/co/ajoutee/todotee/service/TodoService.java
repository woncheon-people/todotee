package kr.co.ajoutee.todotee.service;

import kr.co.ajoutee.todotee.domain.TodoEntity;
import kr.co.ajoutee.todotee.repository.TodoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

@Transactional(readOnly = true)
public class TodoService {
    private final TodoJpaRepository todoRepository;

    @Transactional
    public Long add(TodoEntity todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();

    }

    public TodoEntity searchById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @Transactional
    public void updateTodo(TodoEntity todo, String title, Boolean completed) {
        todo.update(title, completed);
        todoRepository.save(todo);
    }

    @Transactional
    public void deleteTodo(TodoEntity todo) {

        todoRepository.delete(todo);
    }

}
