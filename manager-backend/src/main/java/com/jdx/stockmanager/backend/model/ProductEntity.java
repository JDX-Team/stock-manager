package com.jdx.stockmanager.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="STK_PRODUCTS")
public class ProductEntity {
	
	@Id
	@Column(name="id_product")
	Integer idProduct;
	
	@Column(length=45,nullable=false)
	String name;
	
	@Column(length=200)
	String description;
	
	@Column(length=45)
	String photo;
	
	@Column(name="fec_mod")
	Date fecMod;
	
	@Column(name="fec_delete")
	Date fecDelete;
	
	@OneToMany(mappedBy="product")
	List<ItemEntity> items;

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
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

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	

}
