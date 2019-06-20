package com.yiji.openapi.message.product.ccp;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.StopWatch.TaskInfo;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.annotation.OpenApiMessage;
import com.yiji.openapi.sdk.common.enums.ApiMessageType;
import com.yiji.openapi.sdk.common.message.ApiResponse;



@OpenApiMessage(service = "completeByTaskId", type = ApiMessageType.Response)
public class CompleteByTaskIdResponse extends ApiResponse {
	
	@Length(max = 128)
	@OpenApiField(desc = "流程id")
	private String processInstanceId;
	
	@OpenApiField(desc = "流程是否结束")
	private boolean processIsEnd;
	
	@OpenApiField(desc = "流程集合")
	private List<TaskInfo> tasks;
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	public boolean isProcessIsEnd() {
		return processIsEnd;
	}
	
	public void setProcessIsEnd(boolean processIsEnd) {
		this.processIsEnd = processIsEnd;
	}
	
	public List<TaskInfo> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<TaskInfo> tasks) {
		this.tasks = tasks;
	}
}
