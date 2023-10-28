package kr.co.ajoutee.repository;

import kr.co.ajoutee.domain.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoJpaRepository extends JpaRepository<TodoEntity, Long> {

    Optional<TodoEntity> findById(Long id);
}
