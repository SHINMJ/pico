package com.example.pico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pico.dto.PrizeResponse;
import com.example.pico.dto.ProductRequest;
import com.example.pico.dto.ProductResponse;
import com.example.pico.entity.Prize;
import com.example.pico.entity.Product;
import com.example.pico.exception.BizException;
import com.example.pico.repository.PrizeRepository;
import com.example.pico.repository.ProductRepository;

@Transactional
@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	private final ProductRepository productRepository;
	private final PrizeRepository prizeRepository;

	public ProductService(ProductRepository productRepository, PrizeRepository prizeRepository) {
		this.productRepository = productRepository;
		this.prizeRepository = prizeRepository;
	}
	
	/**
	 * 초기 데이터 설정.
	 */
	@PostConstruct
	public void init() {
		productInit();
		prizeInit();
	}
	
	private void productInit() {
		if (productRepository.count() > 0) {
			return;
		}
		
		this.productRepository.save(Product.of("radio", "라디오", 10000, 10));
		this.productRepository.save(Product.of("tv", "TV", 20000, 10));
		this.productRepository.save(Product.of("refrigerator", "냉장고", 30000, 10));
	}
	
	private void prizeInit() {
		if (prizeRepository.count() > 0) {
			return;
		}
		
		this.prizeRepository.save(Prize.of("이태리타올", 10000, 20000));
		this.prizeRepository.save(Prize.of("이쑤시개", 20000, 30000));
		this.prizeRepository.save(Prize.of("거울", 30000));
	}
	
	public ProductResponse create(ProductRequest request) {
		
		Product saved = productRepository.save(request.toEntity());
		
		return new ProductResponse(saved.id(), saved.code(), saved.name(), saved.price(), saved.quantity());
	}
	
	@Transactional(readOnly = true)
	public List<ProductResponse> findAll(){
		List<Product> products = this.productRepository.findAll();
		
		return products.stream()
				.map(it -> new ProductResponse(it.id(), it.code(), it.name(), it.price(), it.quantity()))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PrizeResponse getPrizeFromProducts(List<Long> productIds) {
		List<Product> products = this.productRepository.findAllById(productIds);
		
		double sum = products.stream()
			.map(product -> product.price())
			.mapToInt(price -> price.intValue())
			.sum();
		logger.info("price sum : {}", sum);
		
		Prize prize = this.prizeRepository.findByMinLessThanEqualAndMaxGreaterThan(sum, sum)
			.orElseThrow(() ->  new BizException("경품이 없습니다.ㅠ"));
		logger.info("경품은? {}", prize.name());
		return new PrizeResponse(prize.id(), prize.name());
		
	}
	

}
