package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member,Integer> {

    public Member findByUsername(String username);

    public Member findByEmail(String email);
}
