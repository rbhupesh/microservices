package com.rb.saga.workflow;

import java.util.List;

public class OrderWorkflow implements Workflow{
	
	private final List<WorkflowTask> tasks;
	
	public OrderWorkflow(List<WorkflowTask> tasks) {
        this.tasks = tasks;
    }

	@Override
	public List<WorkflowTask> getTasks() {
		
		return this.tasks;
	}

}
