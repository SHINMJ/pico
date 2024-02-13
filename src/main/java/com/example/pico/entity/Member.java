package com.example.pico.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
    private String memberId;
    private String password;
    private String name;
    private int age;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
	
    protected Member() {
    	
    }
    
    private Member(String memberId, String password, String name, int age, Gender gender) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
    
    public static Member of(String memberId, String password, String name, int age, Gender gender) {
    	return new Member(memberId, password, name, age, gender);
    }

	public Long id() {
    	return this.id;
    }
    
    public String password() {
		return this.password;
	}
	
	public String name() {
		return this.name;
	}
	
	public String memberId() {
		return this.memberId;
	}
	
	public int age() {
		return this.age;
	}
	
	public String genderString() {
		return this.gender.name();
	}
	
	public void encodedPassword(String encodedPassword) {
		this.password = encodedPassword;
		
	}
    
    @Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(id, other.id);
	}

	



	

}
