package com.rb.saga.tasks;

import java.util.Map;

import javax.inject.Named;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import com.rb.saga.domain.CompositeVO;
import com.rb.saga.enums.WorkflowTaskStatus;
import com.rb.saga.workflow.AbstractTask;

@Named("InventoryTask")
final public class InventoryTask extends AbstractTask {
	
	public InventoryTask(ResteasyClient inventoryClient, Object inventoryRequestDTO) {
		super("InventoryTask");
		this.setStatus(WorkflowTaskStatus.OPEN);
	}

	@Override
	public Boolean execute(Map<String, CompositeVO> maps) {
		System.out.println("InventoryTask.execute() maps :");
		throw new RuntimeException("Exception in Inventory Task");
		//return null;
	}

	@Override
	public Boolean rollbackExecute(Map<String, CompositeVO> maps) {
		System.out.println("InventoryTask.rollbackExecute() maps :");
		return null;
	}

}
