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
@Table(name="STK_ORDERS_ITEMS")
public class OrderItemEntity {
	
	@Id
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_item")
	ItemEntity item;
	
	@Id
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_order")
	OrderEntity order;
	
	@Column
	Integer amount;

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
