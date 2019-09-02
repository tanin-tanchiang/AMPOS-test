package com.example.ampostest.uitils;

public class BillResponse {
	
	private String menuName;
	
	private Long orderId;
	
	private int quantities;
	

	public BillResponse(String menuName, Long orderId, int quantities) {
		super();
		this.menuName = menuName;
		this.orderId = orderId;
		this.quantities = quantities;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
