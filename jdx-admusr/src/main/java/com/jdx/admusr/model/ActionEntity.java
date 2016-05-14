package com.jdx.admusr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity that stores the information of an action
 * 
 */
@Entity
@Table(name="ADM_Acciones")
public class ActionEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_accion")
	private Integer id;
	
	@Column(name="accion",length=50)
	private String action;
	
	@OneToMany(mappedBy="action")
	private List<FunctionalityEntity> functionalities;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the functionalities
	 */
	public List<FunctionalityEntity> getFunctionalities() {
		return functionalities;
	}

	/**
	 * @param functionalities the functionalities to set
	 */
	public void setFunctionalities(List<FunctionalityEntity> functionalities) {
		this.functionalities = functionalities;
	}


	

}
