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

@Entity
@Table(name="STK_STOCK")
public class StockEntity {
	
	@Id
	@Column(name="id_stock")
	Integer idStock;
	
	@Column
	Integer amount;
	
	@Column(name="fec_mod")
	Date fecMod;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_item")
	ItemEntity item;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_location")
	LocationEntity location;

	public Integer getIdStock() {
		return idStock;
	}

	public void setIdStock(Integer idStock) {
		this.idStock = idStock;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getFecMod() {
		return fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public LocationEntity getLocation() {
		return location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}

	

}
