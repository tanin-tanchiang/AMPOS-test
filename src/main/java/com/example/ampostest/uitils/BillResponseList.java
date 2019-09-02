package com.example.ampostest.uitils;

import java.util.List;

import com.example.ampostest.entity.Bill;

public class BillResponseList {
	
	private List<BillResponse> order;

	private int totalPrice;

	public BillResponseList(List<BillResponse> order, int totalPrice) {
		super();
		this.order = order;
		this.totalPrice = totalPrice;
	}

	public List<BillResponse> getOrder() {
		return order;
	}

	public void setOrder(List<BillResponse> order) {
		this.order = order;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
