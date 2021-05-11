package com.rb.rules.service;

import com.rb.rules.entity.Order;

public interface RuleService {
	
	void executeStatefull(Order order);
	
	void executeStateless(Order order);

}
