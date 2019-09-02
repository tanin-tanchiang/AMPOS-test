package com.example.ampostest.uitils;

public class BillCreateRequest {

	private Long menu_id;
	
	private Long orderId;
	
	private int quantities;
	
	public BillCreateRequest(Long menu_id, Long orderId, int quantities) {
		super();
		this.menu_id = menu_id;
		this.orderId = orderId;
		this.quantities = quantities;
	}

	public Long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getQuantities() {
		return quantities;
	}

	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}
	
}
