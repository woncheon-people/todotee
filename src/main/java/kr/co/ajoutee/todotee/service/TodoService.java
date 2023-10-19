package kr.co.ajoutee.todotee.service;

import kr.co.ajoutee.todotee.repository.TodoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoJpaRepository todoRepository;
}
