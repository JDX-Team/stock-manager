package com.jdx.stockmanager.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.jdx.admusr.model.UserEntity;

@Entity
@Table(name="STK_STOCK")
public class StockHistoryEntity {
	
	@Id
	@Column(name="id_stock_history")
	Integer idStockHistory;
	
	@Column(length=45,nullable=false)
	String name;
	
	@Column
	Integer amount;
	
	@Column(length=200)
	Integer comment;
	
	@Id
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_user")
	UserEntity user;
	
	@Id
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_reason")
	ReasonEntity reason;
	
	@Column(name = "fec_add")
	Date fecAdd;

	public Integer getIdStockHistory() {
		return idStockHistory;
	}

	public void setIdStockHistory(Integer idStockHistory) {
		this.idStockHistory = idStockHistory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ReasonEntity getReason() {
		return reason;
	}

	public void setReason(ReasonEntity reason) {
		this.reason = reason;
	}

	public Date getFecAdd() {
		return fecAdd;
	}

	public void setFecAdd(Date fecAdd) {
		this.fecAdd = fecAdd;
	}
	


}
