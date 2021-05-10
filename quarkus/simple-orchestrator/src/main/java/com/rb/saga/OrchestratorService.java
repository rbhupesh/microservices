package com.rb.saga;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;

import com.rb.saga.domain.CompositeVO;
import com.rb.saga.domain.OrchestratorRequestDTO;
import com.rb.saga.enums.WorkflowTaskStatus;
import com.rb.saga.tasks.InventoryTask;
import com.rb.saga.tasks.LogisticTask;
import com.rb.saga.tasks.OrderTask;
import com.rb.saga.tasks.PaymentTask;
import com.rb.saga.workflow.OrderWorkflow;
import com.rb.saga.workflow.Workflow;
import com.rb.saga.workflow.WorkflowTask;

@Singleton
public class OrchestratorService {

	ResteasyClient paymentClient;
	ResteasyClient inventoryClient;
	ResteasyClient orderClient;
	ResteasyClient logisticClient;

	void orderProduct(final OrchestratorRequestDTO requestDTO){
		System.out.println("##### OrchestratorService.orderProduct() STARTED!!!  #####");
		
		Workflow orderWorkflow = this.getOrderWorkflow(requestDTO);
		Map<String, CompositeVO> maps = new HashMap<>();
		
		List<WorkflowTask> list =orderWorkflow.getTasks();		
		WorkflowTaskStatus taskStatus =WorkflowTaskStatus.OPEN;
		
		for (int i = 0 ; i < list.size() && taskStatus!=WorkflowTaskStatus.FAILED; i++) {			
			taskStatus = processTask(list.get(i),maps);
		}
		
		/*status  OPEN --> CLOSE or FAILED
		Above loop can not be replaced by list.stream().foreach() 
		as we need to break the loop as soon as task fails
		avoid throwing exception  as it will work as simple goto statement
		list.forEach((task) -> processTask(task,maps) );
		*/
		
			
		list
		.stream()
		.filter(task -> (task.getStatus()!= WorkflowTaskStatus.OPEN))
		.forEach( (task) -> processRollback(task,maps) );
		
		System.out.println("Print task status:");
		
		orderWorkflow
		.getTasks()
		.forEach(task -> 
		System.out.println(task.getName()+"::"+task.getStatus()));
		
		
		System.out.println("##### OrchestratorService.orderProduct() ENDS!!!  #####");
	}
	
	private WorkflowTaskStatus processTask(WorkflowTask task, Map<String, CompositeVO> maps) {
		try {
			task.execute(maps);
			task.setStatus(WorkflowTaskStatus.FINISH);
			return WorkflowTaskStatus.FINISH;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			task.setStatus(WorkflowTaskStatus.FAILED);
			System.out.println("ERROR :");
			return WorkflowTaskStatus.FAILED;
		}		
	}
	
	private void processRollback(WorkflowTask task, Map<String, CompositeVO> maps) {
		try {
			task.rollbackExecute(maps);
			task.setStatus(WorkflowTaskStatus.ROLLBACKED);
		} catch (Exception e) {
			task.setStatus(WorkflowTaskStatus.FAILED);
			System.out.println("ROLLBACK_ERROR :");
		}		
	}

	
	private Workflow getOrderWorkflow(OrchestratorRequestDTO requestDTO){
		
		WorkflowTask orderTask = new OrderTask(this.orderClient, this.getOrderRequestDTO(requestDTO)); 
		WorkflowTask paymentTask = new PaymentTask(this.paymentClient, this.getPaymentRequestDTO(requestDTO));
        WorkflowTask inventoryTask = new InventoryTask(this.inventoryClient, this.getInventoryRequestDTO(requestDTO));
        WorkflowTask logisticTask = new LogisticTask(this.logisticClient, this.getLogisticRequestDTO(requestDTO));
        
        return new OrderWorkflow(List.of(orderTask,paymentTask, inventoryTask,logisticTask));
    }

	private Object getLogisticRequestDTO(OrchestratorRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getOrderRequestDTO(OrchestratorRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getInventoryRequestDTO(OrchestratorRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getPaymentRequestDTO(OrchestratorRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}

