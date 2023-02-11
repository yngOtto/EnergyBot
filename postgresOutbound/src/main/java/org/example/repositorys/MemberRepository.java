package org.example.repositorys;

import org.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findAllByToken(String token);

  void deleteAllByEmail(String email);

  Optional<Member> findMembersByEmail(String email);

  Optional<Member> findAllByEmailAndPassword(String email, String password);
}
