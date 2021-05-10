package com.rb.saga.workflow;

import com.rb.saga.enums.WorkflowTaskStatus;

public abstract class AbstractTask implements WorkflowTask {
	
	private WorkflowTaskStatus status;
	private String name;
	
	public AbstractTask(String name) {
		this.name = name;
	}

	@Override
	final public WorkflowTaskStatus getStatus() {
		return status;
	}

	@Override
	final public void setStatus(WorkflowTaskStatus status) {
		this.status = status;
	}

	@Override
	final public String getName() {
		return name;
	}
}
