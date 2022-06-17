package org.sid.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categories {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategory;
	
	private String name;
	
	public Categories(Long idCategory, String name) {
		this.idCategory = idCategory;
		this.name = name;
	}
	
	
	
	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categories() {
	}
	

	

}
