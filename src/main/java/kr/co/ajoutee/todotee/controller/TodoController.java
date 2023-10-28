package kr.co.ajoutee.todotee.controller;

import jakarta.validation.Valid;
import kr.co.ajoutee.todotee.domain.TodoEntity;
import kr.co.ajoutee.todotee.dto.TodoRequestDto;
import kr.co.ajoutee.todotee.dto.TodoResponseDto;
import kr.co.ajoutee.todotee.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;


    @PostMapping("")
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody TodoRequestDto todoRequestDto) {
        TodoEntity todo = todoRequestDto.toEntity();
        todoService.add(todo);
        TodoResponseDto todoResponseDto = TodoResponseDto.of(todo);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TodoResponseDto>> searchAll() {
        List<TodoEntity> result = todoService.searchAll();
        List<TodoResponseDto> todoList = result.stream().map(e -> TodoResponseDto.of(e)).toList();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<TodoResponseDto> searchById(@PathVariable Long id) {
        TodoEntity result = todoService.searchById(id);
        TodoResponseDto todoResponseDto = TodoResponseDto.of(result);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequestDto todoRequestDto) {
        log.info("id = {}, title = {}, completed = {}", id, todoRequestDto.getTitle(), todoRequestDto.getCompleted());
        TodoEntity result = todoService.searchById(id);
        todoService.updateTodo(result, todoRequestDto.getTitle(),todoRequestDto.getCompleted());
        TodoResponseDto todoResponseDto = TodoResponseDto.of(result);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoResponseDto> deleteTodo(@PathVariable Long id) {
        TodoEntity result = todoService.searchById(id);
        todoService.deleteTodo(result);
        return ResponseEntity.noContent().build();
    }

}
