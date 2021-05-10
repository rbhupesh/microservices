# Simple Orchestrator project

This project uses Quarkus Java Framework. (https://quarkus.io/)

> **_NOTE:_**  This orchestrator is stateful blocking implementation of saga pattern further enhancement to this orchestrator is externalise state from orchestrator and integrate with persistent queue (e.g. rabit mq or kafka)

It creates workflow with set of ordered task and tries to execute task in a sequence.
If any task fails it stops the execution for rest of the task and tries to execute rollback for executed task.

task.execute() & task.rollbackExecute() can be annoteted with 
@Timeout , @Fallback , @Retry annotation

## Running test case

You can run your application in dev mode that enables live coding using:
```shell script
mvn clean compile test 
```
Its output as follows

'##### OrderResource.addOrder() STARTED!!!  #####
'##### OrchestratorService.orderProduct() STARTED!!!  #####
OrderTask.execute() maps :
PaymentTask.execute() maps :
InventoryTask.execute() maps :
ERROR :
OrderTask.rollbackExecute() maps :
PaymentTask.rollbackExecute() maps :
InventoryTask.rollbackExecute() maps :
Print task status:
OrderTask::ROLLBACKED
OrderTask::ROLLBACKED
InventoryTask::ROLLBACKED
LogisticTask::OPEN
'##### OrchestratorService.orderProduct() ENDS!!!  #####
'##### ResponseEntity booking:#
rbhupesh,sa70,NAN,0.0,
'##### OrderResource.addOrder() executed!!!  #####


