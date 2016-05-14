package com.jdx.test.common.model;

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
@Table(name="ADM_GenericRepo")
public class GenericEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name",length=50)
	private String name;
	
	@OneToMany(mappedBy="ent")
	private List<GenericRelEntity> rels;


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rels
	 */
	public List<GenericRelEntity> getRels() {
		return rels;
	}

	/**
	 * @param rels the rels to set
	 */
	public void setRels(List<GenericRelEntity> rels) {
		this.rels = rels;
	}

}
