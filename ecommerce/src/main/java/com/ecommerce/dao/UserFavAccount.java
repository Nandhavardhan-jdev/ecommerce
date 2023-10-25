package com.ecommerce.dao;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_FAV_ACCOUNT")
public class UserFavAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item itemId;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User userId;
	@Column(name = "ITEM_NAME")
	private String itemName;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	@Column(name = "ITEM_STATUS")
	private boolean itemStatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getItemId() {
		return itemId;
	}
	public void setItemId(Item itemId) {
		this.itemId = itemId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
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
