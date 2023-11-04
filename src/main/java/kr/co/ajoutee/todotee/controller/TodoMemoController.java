package kr.co.ajoutee.todotee.controller;

import jakarta.validation.Valid;
import kr.co.ajoutee.todotee.domain.TodoMemo;
import kr.co.ajoutee.todotee.dto.TodoRequestMemoDto;
import kr.co.ajoutee.todotee.dto.TodoResponseMemoDto;
import kr.co.ajoutee.todotee.service.TodoMemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class TodoMemoController {

    private final TodoMemoService todoMemoService;

    @PostMapping("")
    public ResponseEntity<TodoResponseMemoDto> createMemo(@Valid @RequestBody TodoRequestMemoDto todoRequestMemoDto){
        TodoMemo todoMemo = todoRequestMemoDto.toEntity();
        todoMemoService.addMemo(todoMemo);
        TodoResponseMemoDto todoResponseMemoDto = TodoResponseMemoDto.of(todoMemo);
        return new ResponseEntity<>(todoResponseMemoDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponseMemoDto> searchMemoById(@PathVariable Long id){
        TodoMemo result = todoMemoService.searchMemoById(id);
        TodoResponseMemoDto todoResponseMemoDto = TodoResponseMemoDto.of(result);
        return new ResponseEntity<>(todoResponseMemoDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TodoMemo>> searchAllMemo(){
        List<TodoMemo> result = todoMemoService.searchAllMemo();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponseMemoDto> updateMemo(@PathVariable Long id, @Valid @RequestBody TodoRequestMemoDto todoRequestMemoDto){
        TodoMemo result = todoMemoService.searchMemoById(id);
        todoMemoService.updateMemo(result, todoRequestMemoDto.getTitle(),todoRequestMemoDto.getMemo(), todoRequestMemoDto.getCompleted());
        TodoResponseMemoDto todoResponseMemoDto = TodoResponseMemoDto.of(result);
        return new ResponseEntity<>(todoResponseMemoDto,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoResponseMemoDto> deleteMemo(@PathVariable Long id){
        TodoMemo result = todoMemoService.searchMemoById(id);
        todoMemoService.deleteMemo(result);
        return ResponseEntity.noContent().build();
    }
}
