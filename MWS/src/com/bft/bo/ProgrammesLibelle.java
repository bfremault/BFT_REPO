package com.bft.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "programmes")
public class ProgrammesLibelle {
	
	@DatabaseField (id = true)
	private Integer id;
	@DatabaseField
	private String libelle;
	
	public ProgrammesLibelle() {
		// TODO Auto-generated constructor stub
	}
	
	public ProgrammesLibelle(Integer id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
