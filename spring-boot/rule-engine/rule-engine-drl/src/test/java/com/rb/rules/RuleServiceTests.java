package com.rb.rules;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rb.rules.entity.Order;
import com.rb.rules.service.RuleService;

@SpringBootTest
class RuleServiceTests {
	
	@Autowired
	RuleService ruleService;
	
	@Test
	void executeStatefull() {	
		
		Order order = new Order();
		order.setName("mobile");
		order.setCardType("HDFC");
		order.setPrice(11000);
	
		ruleService.executeStatefull(order);
		
		assertThat(order.getDiscount()).isEqualTo(10);		
	}
	
	@Test
	void executeStateless() {	
		
		Order order = new Order();
		order.setName("mobile");
		order.setCardType("HDFC");
		order.setPrice(11000);
	
		ruleService.executeStateless(order);
		
		assertThat(order.getDiscount()).isEqualTo(0);		
	}
	
}
