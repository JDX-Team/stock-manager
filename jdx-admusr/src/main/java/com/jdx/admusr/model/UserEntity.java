package com.jdx.admusr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * Entity that stores an user for security purpose
 *
 */
@Entity
@Table(name="ADM_Usuarios")
@FetchProfile(
		name = "user_with_rol",
		fetchOverrides = {
				@FetchProfile.FetchOverride(
						entity = UserEntity.class, 
						association = "roles", 
						mode = FetchMode.JOIN)
		})
@DefaultFecthProfile("user_with_rol")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario")
	private Integer id;
	
	@Column(name="usuario",length=50)
	private String user;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name="password",length=100)
	private String password;

	@Version
	@Column(name="fec_mod")
	private Date fecMod;
	
	
	@ManyToMany()
	//@Cascade(CascadeType.SAVE_UPDATE)
	@JoinTable(name = "ADM_ER_usuarios_roles", joinColumns = { 
			@JoinColumn(name = "id_usuario", nullable = false, updatable = false) }, 
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
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the fecMod
	 */
	public Date getFecMod() {
		return fecMod;
	}


	/**
	 * @param fecMod the fecMod to set
	 */
	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}


	/**
	 * @return the roles
	 */
	public List<RoleEntity> getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
}
