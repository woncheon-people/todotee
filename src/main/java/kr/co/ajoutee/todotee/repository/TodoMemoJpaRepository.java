package kr.co.ajoutee.todotee.repository;

import kr.co.ajoutee.todotee.domain.TodoMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoMemoJpaRepository extends JpaRepository<TodoMemo,Long> {

    Optional<TodoMemo> findById(Long id);
}
