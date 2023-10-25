package com.ecommerce.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "VENDOR")
public class Vendor {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "NAME")
	private String name;
	@Pattern(regexp = "[a-zA-Z0-9_.]+@gmail.com" , message = "email id should be in format")
	@Column(name = "EMAIL_ID")
	private String emailId;
	@Column(name = "MOBILE_NO")
	@Pattern(regexp = "\\d{10}" , message = "mobile No should be in 10 digits")
	private String mobileNo;
	@Column(name = "BALANCE")
	private BigDecimal balance;
	@Column(name = "DOJ")
	@CurrentTimestamp
	@Temporal(TemporalType.DATE)
	private Date doj;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "CREATED_ON",updatable = false)
	@CurrentTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	@Column(name = "UPDATED_ON")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	@OneToMany(mappedBy = "vendorId")
	private List<Order> order;
	
	@Column(name = "AVAILABILTY")
	private String availability;
	
	
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
}
