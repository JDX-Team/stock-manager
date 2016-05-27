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
@Table(name="STK_ITEMS")
public class ItemEntity {
	
	@Id
	@Column(name="id_item")
	Integer idItem;
	
	@Column(length=45,nullable=false)
	String name;
	
	@Column(length=45)
	String photo;
	
	@Column(name="fec_mod")
	Date fecMod;
	
	@Column(name="fec_delete")
	Date fecDelete;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_product")
	ProductEntity product;

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getFecMod() {
		return fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

	public Date getFecDelete() {
		return fecDelete;
	}

	public void setFecDelete(Date fecDelete) {
		this.fecDelete = fecDelete;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}


}
