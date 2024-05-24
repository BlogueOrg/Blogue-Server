package com.blogue.blogue.member.repository;

import com.blogue.blogue.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository holds basic CRUD functions
// without the @Repository annotation, IoC works fine since it inherits JpaRepository
// automatically registered as Bean
public interface MemberRepository extends JpaRepository<Member, Long> {

    // JPA Query Method
    //   the query is automatically called
    //   select * from user where username = 1?
    List<Member> findByUsername(String username);
}
