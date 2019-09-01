package com.example.ampostest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the menu.")
@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "name")
	@Size(min = 1, max = 255, message = "Name shold have atleast 1 characters")
	private String name;
	
	@Column(name = "description")
	@ApiModelProperty(notes = "Description of menu")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "price")
	private int price;
	
	protected Menu() {
		
	}
	
	public Menu(String name, String description , String image, int price) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id2) {
		this.id = id2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
