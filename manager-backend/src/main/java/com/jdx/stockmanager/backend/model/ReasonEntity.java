package com.jdx.stockmanager.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STK_REASONS")
public class ReasonEntity {
	
	@Id
	@Column(name="id_reason")
	Integer idReason;
	
	@Column(length=45,nullable=false)
	String reason;

	public Integer getIdReason() {
		return idReason;
	}

	public void setIdReason(Integer idReason) {
		this.idReason = idReason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}	

}
