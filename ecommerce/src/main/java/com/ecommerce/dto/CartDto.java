package com.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.dao.Item;

public class CartDto {

	private long id;
	private List<Item> itemIds;
	private BigDecimal amount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public List<Item> getItemIds() {
		return itemIds;
	}
	public void setItemIds(List<Item> itemIds) {
		this.itemIds = itemIds;
	}
	
}
