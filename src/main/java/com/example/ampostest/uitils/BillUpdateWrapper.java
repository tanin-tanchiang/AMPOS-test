package com.example.ampostest.uitils;

import com.example.ampostest.entity.Bill;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class BillUpdateWrapper {
	@ApiModelProperty(notes = "Object of menu_id ,order_id ,quantities" )
	private BillCreateRequest billCreateRequest;
	
	@ApiModelProperty(notes = "Action to Update bill it can be 'update', 'add', 'remove'")
	private String status;

	public BillUpdateWrapper(BillCreateRequest billCreateRequest, String status) {
		super();
		this.billCreateRequest = billCreateRequest;
		this.status = status;
	}
	public BillCreateRequest getBillCreateRequest() {
		return billCreateRequest;
	}
	public void setBillCreateRequest(BillCreateRequest billCreateRequest) {
		this.billCreateRequest = billCreateRequest;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	
}
