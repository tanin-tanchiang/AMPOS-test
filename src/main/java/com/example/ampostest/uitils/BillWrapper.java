package com.example.ampostest.uitils;

import java.util.List;

import com.example.ampostest.entity.Bill;

public class BillWrapper {

	private List<Bill> order;

	private int totalPrice;

	public BillWrapper(List<Bill> order, int totalPrice) {
		super();
		this.order = order;
		this.totalPrice = totalPrice;
	}

	public List<Bill> getOrder() {
		return order;
	}

	public void setOrder(List<Bill> order) {
		this.order = order;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
