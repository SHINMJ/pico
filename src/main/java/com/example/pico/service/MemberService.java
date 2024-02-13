package com.example.pico.service;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pico.dto.MemberRequest;
import com.example.pico.dto.MemberResponse;
import com.example.pico.entity.Gender;
import com.example.pico.entity.Member;
import com.example.pico.repository.MemberRepository;

@Transactional
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * 초기 데이터 설정.
	 */
	@PostConstruct
	public void init() {
		if (memberRepository.count() > 0) {
			return;
		}
		
		create(new MemberRequest("test", "1111", "testUser", 20, Gender.male.name()));
	}
	
	public MemberResponse create(MemberRequest request) {
	
		Member member = request.toEntity();
		member.encodedPassword(passwordEncoder.encode(member.password()));
		
		Member saved = memberRepository.save(member);
		
		return new MemberResponse(saved.memberId(), saved.name(), saved.age(), saved.genderString());
	}

	
	

}
