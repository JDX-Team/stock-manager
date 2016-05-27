package com.jdx.stockmanager.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="STK_LOCATIONS")
public class LocationEntity {
	
	@Id
	@Column(name="id_location")
	Integer idStock;
	
	@Column(length=45,nullable=false)
	String name;
	
	@Column(length=200,nullable=false)
	String description;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_warehouse")
	WarehouseEntity warehouse;

	public Integer getIdStock() {
		return idStock;
	}

	public void setIdStock(Integer idStock) {
		this.idStock = idStock;
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

	public WarehouseEntity getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseEntity warehouse) {
		this.warehouse = warehouse;
	}

	

}
