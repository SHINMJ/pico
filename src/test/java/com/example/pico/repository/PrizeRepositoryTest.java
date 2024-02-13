package com.example.pico.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.pico.entity.Prize;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
class PrizeRepositoryTest {
	
	@Autowired
	PrizeRepository prizeRepository;

	@Test
	void findByMinLessThanEqualAndMaxGreaterThan() {
		Prize 경품1 = Prize.of("경품1", 10000, 20000);
        Prize 경품2 =Prize.of("경품2", 20000, 30000);
        Prize 경품3 = Prize.of("경품3", 30000);
        
        prizeRepository.save(경품1);
        prizeRepository.save(경품2);
        prizeRepository.save(경품3);
        prizeRepository.flush();

        Optional<Prize> optional = prizeRepository.findByMinLessThanEqualAndMaxGreaterThan(14000, 14000);

        assertTrue(optional.isPresent());
        Prize prize = optional.get();
        assertTrue(prize.name().equals(경품1.name()));
	}

}
