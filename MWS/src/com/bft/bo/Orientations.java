package com.bft.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "orientations")

public class Orientations {

	@DatabaseField (id = true)
	private Integer id_orientation;
	@DatabaseField
	private String libelle;
	@DatabaseField
	private String libelle_court;
//	@DatabaseField
//	private String resource;
	
	public Orientations() {
		super();
	}

	public Orientations(Integer id_orientation, String libelle,
			String libelle_court,String resource) {
		super();
		this.id_orientation = id_orientation;
		this.libelle = libelle;
		this.libelle_court = libelle_court;
	//	this.resource = resource;

	}

	public Integer getId_orientation() {
		return id_orientation;
	}

	public void setId_orientation(Integer id_orientation) {
		this.id_orientation = id_orientation;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle_court() {
		return libelle_court;
	}

	public void setLibelle_court(String libelle_court) {
		this.libelle_court = libelle_court;
	}	
	public String toString() {
		return libelle_court;
	}
//	public String getResource() {
//		return resource;
//	}
//	public void setResource(String resource) {
//		this.resource = resource;
//	}
}