package com.rb.saga.tasks;

import java.util.Map;

import javax.inject.Named;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import com.rb.saga.domain.CompositeVO;
import com.rb.saga.enums.WorkflowTaskStatus;
import com.rb.saga.workflow.AbstractTask;

@Named("OrderTask")
final public class OrderTask extends AbstractTask {
	
	public OrderTask(ResteasyClient orderClient, Object orderRequestDTO) {
		super("OrderTask");
		this.setStatus(WorkflowTaskStatus.OPEN);
	}

	@Override
	public Boolean execute(Map<String, CompositeVO> maps) {
		System.out.println("OrderTask.execute() maps :");		
		return null;
	}

	@Override
	public Boolean rollbackExecute(Map<String, CompositeVO> maps) {
		System.out.println("OrderTask.rollbackExecute() maps :");		
		return null;
	}
}
