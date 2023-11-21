package kr.co.ajoutee.todotee.repository;

import kr.co.ajoutee.todotee.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findFirstByEmail(String email);
}
