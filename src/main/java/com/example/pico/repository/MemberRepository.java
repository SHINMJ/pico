package com.example.pico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pico.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Optional<Member> findByMemberId(String memberId);
}
