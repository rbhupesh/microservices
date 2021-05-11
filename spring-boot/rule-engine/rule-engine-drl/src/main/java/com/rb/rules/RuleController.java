package com.rb.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rb.rules.entity.Order;
import com.rb.rules.service.RuleService;

@RestController
public class RuleController {
		
	@Autowired
	RuleService ruleService;
	
	
	@PostMapping("/api/v1/order")
	public Order orderNowV1(@RequestBody Order order) {
		System.out.println("RuleController.orderNow Started");
		System.out.println("order:" +order);
		
		ruleService.executeStatefull(order);
		
		System.out.println("RuleController.orderNow STOPED");
		return order;
	}
	
	@PostMapping("/api/v2/order")
	public Order orderNowV2(@RequestBody Order order) {
		System.out.println("RuleController.orderNowV2 Started");
		System.out.println("order:" +order);
				
		ruleService.executeStateless(order);
				
		System.out.println("order after rule execution :" +order);
		System.out.println("RuleController.orderNow STOPED");
		return order;
	}

}
