package kr.co.ajoutee.controller;

import jakarta.validation.Valid;
import kr.co.ajoutee.domain.TodoEntity;
import kr.co.ajoutee.dto.TodoRequest;
import kr.co.ajoutee.dto.TodoResponse;
import kr.co.ajoutee.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;


    @PostMapping("")
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest todoRequest) {
        if(ObjectUtils.isEmpty(todoRequest.getTitle()))
            return ResponseEntity.badRequest().build();
        if(ObjectUtils.isEmpty(todoRequest.getCompleted()))
            todoRequest.setCompleted(false);
        if (ObjectUtils.isEmpty(todoRequest.getUpdated())) {
            todoRequest.setUpdated(new Date());
        }
        TodoEntity result = this.todoService.add(todoRequest);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> searchTodo() {
        List<TodoEntity> list = this.todoService.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> searchById(@PathVariable Long id) {
        TodoEntity result = this.todoService.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody TodoRequest request) {
        TodoEntity result = this.todoService.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));


    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoResponse> deleteTodo(@PathVariable Long id) {
        TodoEntity todoEntity = todoService.searchById(id);
        todoService.deleteById(todoEntity.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllTodo() {
        this.todoService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
