package kr.co.ajoutee.service;

import kr.co.ajoutee.repository.TodoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoJpaRepository todoRepository;
}
