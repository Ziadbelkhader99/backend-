package org.sid.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Dependency {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private Date date;
	private Double amount;
	private String categorie;
	
	
	
	public Dependency() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Dependency(Long id, Date date, Double amount, String categorie) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.categorie = categorie;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	

}
