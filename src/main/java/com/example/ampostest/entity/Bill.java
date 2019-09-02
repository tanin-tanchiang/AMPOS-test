package com.example.ampostest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "About the bill .")
@Entity
@Table(name = "bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "order_id")
	private Long orderId;
	
	@ManyToOne
    @JoinColumn(name="menu_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Menu menu;
	
	@Column(name = "quantities")
	private int quantities;

	public Bill() {
		
	}
	

	public Bill(Long orderId, Menu menu, int quantities) {
		super();
		this.orderId = orderId;
		this.menu = menu;
		this.quantities = quantities;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getQuantities() {
		return quantities;
	}

	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}
	
	
}
