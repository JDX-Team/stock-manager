package com.jdx.stockmanager.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STK_WAREHOUSE")
public class WarehouseEntity {
	
	@Id
	@Column(name="id_warehouse")
	Integer idWarehouse;
	
	@Column(length=45,nullable=false)
	String name;

	public Integer getIdWarehouse() {
		return idWarehouse;
	}

	public void setIdWarehouse(Integer idWarehouse) {
		this.idWarehouse = idWarehouse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	

}
