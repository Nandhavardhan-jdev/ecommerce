package com.ecommerce.dto;

import java.math.BigDecimal;

public class UserFavAcDto {

	private long id;
	private long itemId;
	private long userId;
	private String itemName;
	private String description;
	private BigDecimal amount;
	private boolean itemStatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public boolean isItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(boolean itemStatus) {
		this.itemStatus = itemStatus;
	}
	
}
