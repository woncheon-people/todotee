package kr.co.ajoutee.todotee.controller;

import jakarta.validation.Valid;
import kr.co.ajoutee.domain.TodoEntity;
import kr.co.ajoutee.todotee.dto.TodoRequestDto;
import kr.co.ajoutee.todotee.dto.TodoResponseDto;
import kr.co.ajoutee.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
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


    @GetMapping("{id}")
    public ResponseEntity<TodoResponseDto> searchById(@PathVariable Long id) {
        TodoEntity result = todoService.searchById(id);
        TodoResponseDto todoResponseDto = TodoResponseDto.of(result);
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequestDto todoRequestDto) {
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
