package kr.co.ajoutee.todotee.service;

import kr.co.ajoutee.todotee.domain.TodoMemo;
import kr.co.ajoutee.todotee.repository.TodoMemoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoMemoService {

    private final TodoMemoJpaRepository todoMemoRepository;

    @Transactional
    public Long addMemo(TodoMemo todo){
        todoMemoRepository.save(todo);
        return todo.getId();
    }

    public List<TodoMemo> searchAllMemo(){ return todoMemoRepository.findAll(); }

    public TodoMemo searchMemoById(Long id){
        return todoMemoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void updateMemo(TodoMemo todo, String title,String memo, Boolean completed) { todo.update(title,memo,completed);}

    @Transactional
    public void deleteMemo(TodoMemo todo){
        todoMemoRepository.delete(todo);
    }
}
