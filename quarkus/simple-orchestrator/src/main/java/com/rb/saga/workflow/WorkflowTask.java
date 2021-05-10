package com.rb.saga.workflow;

import java.util.Map;

import com.rb.saga.domain.CompositeVO;
import com.rb.saga.enums.WorkflowTaskStatus;

public interface WorkflowTask {
		
	String getName();
	
	WorkflowTaskStatus getStatus();
	
	void setStatus(WorkflowTaskStatus status);	
	
	Boolean execute(Map<String, CompositeVO> maps);	
	
	Boolean rollbackExecute(Map<String, CompositeVO> maps);
}
