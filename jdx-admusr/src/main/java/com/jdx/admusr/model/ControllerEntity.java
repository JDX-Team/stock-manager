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
 * Entity that stores the information of a view
 * 
 */
@Entity
@Table(name="ADM_Controllers")
public class ControllerEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_controller")
	private Integer id;
	
	@Column(name="controller",length=50)
	private String controller;
	
	@OneToMany(mappedBy="controller")
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
	 * @return the controller
	 */
	public String getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(String controller) {
		this.controller = controller;
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
