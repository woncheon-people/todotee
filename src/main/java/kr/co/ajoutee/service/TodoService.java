package kr.co.ajoutee.service;

import kr.co.ajoutee.domain.TodoEntity;
import kr.co.ajoutee.dto.TodoRequest;
import kr.co.ajoutee.repository.TodoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoJpaRepository todoRepository;

    public TodoEntity add(TodoRequest request) {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTitle(request.getTitle());
            todoEntity.setUpdated(request.getUpdated());
            todoEntity.setCompleted(request.getCompleted());
            return this.todoRepository.save(todoEntity);
        }

    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();

    }
    public TodoEntity searchById(Long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);
        if (request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }
        if (request.getCompleted() != null) {
            todoEntity.setCompleted(request.getCompleted());
        }
        if(request.getUpdated() != null){
            todoEntity.setUpdated(request.getUpdated());

        }
        return this.todoRepository.save(todoEntity);
    }

    public void deleteAll(){
        todoRepository.deleteAll();
    }

    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);
    }
}
