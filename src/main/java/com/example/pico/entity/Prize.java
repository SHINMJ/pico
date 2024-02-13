package com.example.pico.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.pico.exception.BizException;

@Entity
@Table(name = "prize")
public class Prize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private double min;
	private double max;
	
	protected Prize() {
		
	}
	
	private Prize(String name, double min, double max) {
		validate(min, max);
		this.name = name;
		this.min = min;
		this.max = max;
	}
	
	public static Prize of(String name, double min) {
		return new Prize(name, min, 99999999);
	}
	
	public static Prize of(String name, double min, double max) {
		return new Prize(name, min, max);
	}
	
	public Long id() {
		return this.id;
	}
	
	public String name() {
		return this.name;
	}
	
	private void validate(double min, double max) {
		if (min > max) {
			throw new BizException("최소값이 최대값보다 큽니다.");
		}
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
		Prize other = (Prize) obj;
		return Objects.equals(id, other.id);
	}
	
}
