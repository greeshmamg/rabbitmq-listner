package com.example.demo.rabbitmq.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;



@Component
public class OrderDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int orderId;
	
	private Order orderedItem;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Order getOrderdItem() {
		return orderedItem;
	}

	public void setOrderdItem(Order orderdItem) {
		this.orderedItem = orderdItem;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", orderdItem=" + orderedItem + "]";
	}
}
