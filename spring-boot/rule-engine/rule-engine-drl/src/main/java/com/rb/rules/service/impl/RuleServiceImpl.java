package com.rb.rules.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.rb.rules.entity.Order;
import com.rb.rules.service.RuleService;

public class RuleServiceImpl implements RuleService{

	@Autowired
	@Qualifier("KieContainer1")
	private KieContainer kieContainer1;
	
	@Autowired
	@Qualifier("kieSession2")
	private StatelessKieSession kieSession2;
	

	@Override
	public void executeStatefull(Order order) {
		System.out.println("RuleServiceImpl.executeStatefull Started");
		
		KieSession kieSession = kieContainer1.newKieSession();				
		kieSession.insert(order);
		int count =kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println("order after rule execution :" +order
				+"\ncount:"+count);
		
		System.out.println("RuleServiceImpl.executeStatefull Finished");
	}
	
	@Override
	public void executeStateless(Order order) {
		System.out.println("RuleServiceImpl.executeStateless Started");
		
		kieSession2.execute(order);
		
		System.out.println("RuleServiceImpl.executeStateless Finished");
	}

}
