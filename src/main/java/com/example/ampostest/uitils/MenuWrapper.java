package com.example.ampostest.uitils;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;

import com.example.ampostest.entity.Menu;

import io.swagger.annotations.ApiModelProperty;

public class MenuWrapper {
	
	private String name;
	
	private String description;

	private String image;

	private int price;
	
    private List<String> additionalDetails;
    
	public MenuWrapper(String name, String description, String image, int price, List<String> additionalDetails) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.additionalDetails = additionalDetails;
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
	public List<String> getAdditionalDetails() {
		return additionalDetails;
	}
	public void setAdditionalDetails(List<String> additionalDetails) {
		this.additionalDetails = additionalDetails;
	}
	
    
}
