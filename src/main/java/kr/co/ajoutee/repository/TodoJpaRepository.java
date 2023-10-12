package kr.co.ajoutee.repository;

import kr.co.ajoutee.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
}
