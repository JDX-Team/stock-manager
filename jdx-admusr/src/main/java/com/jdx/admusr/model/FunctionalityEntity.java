package com.jdx.admusr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entity for store a functionality (represent one action that can be performed on a controller)
 * for security purpose
 *
 */
@Entity
@Table(name="ADM_Funcionalidades")
public class FunctionalityEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_func")
	private Integer id;
	

	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_controller")
	private ControllerEntity controller;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_accion")
	private ActionEntity action;
	
	@ManyToMany
	@JoinTable(name = "ADM_ER_rol_funcionalidad", joinColumns = { 
			@JoinColumn(name = "id_func", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_rol", 
					nullable = false, updatable = false) })
	private List<RoleEntity> roles;
	

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
	public ControllerEntity getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(ControllerEntity view) {
		this.controller = view;
	}

	/**
	 * @return the action
	 */
	public ActionEntity getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(ActionEntity action) {
		this.action = action;
	}

	/**
	 * @return the roles
	 */
	public List<RoleEntity>  getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleEntity>  roles) {
		this.roles = roles;
	}



}
