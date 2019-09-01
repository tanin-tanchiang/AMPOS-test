package com.example.ampostest.entity;

public class Bill {
	private Integer id;
	
	private Integer menu_id;
	
	private int Quantities;
	

	public Bill(Integer id, Integer menu_id, int quantities) {
		super();
		this.id = id;
		this.menu_id = menu_id;
		Quantities = quantities;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public int getQuantities() {
		return Quantities;
	}

	public void setQuantities(int quantities) {
		Quantities = quantities;
	}
	
	
}
