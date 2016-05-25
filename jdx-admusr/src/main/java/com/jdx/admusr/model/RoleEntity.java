package com.jdx.admusr.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import com.jdx.common.annotation.DefaultFecthProfile;
/**
 * Entity for store a role for security purpose
 *
 */
@Entity
@Table(name="ADM_Roles")
@FetchProfile(
		name = "rol_with_function",
		fetchOverrides = {
				@FetchProfile.FetchOverride(
						entity = RoleEntity.class, 
						association = "functionalities", 
						mode = FetchMode.JOIN)
		})
@DefaultFecthProfile("rol_with_function")
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_rol")
	private Integer id;
	
	@Column(name="rol",length=50)
	private String rol;
	
	@Version
	@Column(name="fec_mod")
	private Timestamp fecMod;
	
	@ManyToMany
	@JoinTable(name = "ADM_ER_rol_funcionalidad", joinColumns = { 
			@JoinColumn(name = "id_rol", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_func", 
					nullable = false, updatable = false) })
	private List<FunctionalityEntity> functionalities;
	
	@ManyToMany
	@JoinTable(name = "ADM_ER_usuarios_roles", joinColumns = { 
			@JoinColumn(name = "id_rol", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_usuario", 
					nullable = false, updatable = false) })
	private List<UserEntity> users;

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
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the fecMod
	 */
	public Timestamp getFecMod() {
		return fecMod;
	}

	/**
	 * @param fecMod the fecMod to set
	 */
	public void setFecMod(Timestamp fecMod) {
		this.fecMod = fecMod;
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

	/**
	 * @return the usuarios
	 */
	public List<UserEntity> getUsers() {
		return users;
	}

	/**
	 * @param users the usuarios to set
	 */
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}


}
