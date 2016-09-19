package com.jdx.master.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jdx.master.annotation.MasterTable;

/**
 * Entity that stores the information of an action
 * 
 */
//@Entity
@Table(name="Estados")
@MasterTable(name="Estados")
public class EstadoEntity{
	
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="id_grupo")
	private String idGrupo;
	
	@Column(name="estado")
	private String estado;

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
	 * @return the idGrupo
	 */
	public String getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
