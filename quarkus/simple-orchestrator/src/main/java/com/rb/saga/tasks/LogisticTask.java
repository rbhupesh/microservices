package com.rb.saga.tasks;

import java.util.Map;

import javax.inject.Named;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import com.rb.saga.domain.CompositeVO;
import com.rb.saga.enums.WorkflowTaskStatus;
import com.rb.saga.workflow.AbstractTask;

@Named("LogisticTask")
final public class LogisticTask extends AbstractTask {
	
	public LogisticTask(ResteasyClient inventoryClient, Object inventoryRequestDTO) {
		super("LogisticTask");
		this.setStatus(WorkflowTaskStatus.OPEN);
	}

	@Override
	public Boolean execute(Map<String, CompositeVO> maps) {
		System.out.println("LogisticTask.execute() maps :");		
		return null;
	}

	@Override
	public Boolean rollbackExecute(Map<String, CompositeVO> maps) {
		System.out.println("LogisticTask.rollbackExecute() maps :");
		return null;
	}

}
