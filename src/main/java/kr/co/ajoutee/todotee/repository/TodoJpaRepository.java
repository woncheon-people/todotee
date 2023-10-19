package kr.co.ajoutee.todotee.repository;

import kr.co.ajoutee.todotee.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
}
