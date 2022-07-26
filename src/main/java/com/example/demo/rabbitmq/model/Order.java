package com.example.demo.rabbitmq.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Order  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productType;
	private Integer itemId;
	private Integer quantity;
	private Double price;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [productType=" + productType + ", itemId=" + itemId + ", quantity=" + quantity + ", price="
				+ price + "]";
	}

}
