package com.jdx.stockmanager.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STK_ORDERS")
public class OrderEntity {
	
	@Id
	@Column(name="id_order")
	Integer idOrder;
	
	@Column(length=45,nullable=false)
	String supplier;
	
	@Column(name="fec_order")
	Date fecOrder;

	public Integer getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getFecOrder() {
		return fecOrder;
	}

	public void setFecOrder(Date fecOrder) {
		this.fecOrder = fecOrder;
	}

	

}
