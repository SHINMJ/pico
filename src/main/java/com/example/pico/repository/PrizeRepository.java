package com.example.pico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pico.entity.Prize;

public interface PrizeRepository extends JpaRepository<Prize, Long>{
    Optional<Prize> findByMinLessThanEqualAndMaxGreaterThan(double min, double max);
}
