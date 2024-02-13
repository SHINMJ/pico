package com.example.pico.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.pico.entity.Gender;
import com.example.pico.entity.Member;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
class MemberRepositoryTest {
	
	@Autowired
	MemberRepository memberRepository;

	@Test
	void findByMemberId() {
		Member user = Member.of("test", "1111", "testuser", 20, Gender.female);
		
		memberRepository.save(user);
		
		memberRepository.flush();
		
		
		Optional<Member> op = memberRepository.findByMemberId(user.memberId());
		
		assertTrue(op.isPresent());
		Member findUser = op.get();
		assertTrue(findUser.name().equals(user.name()));
	}

}
