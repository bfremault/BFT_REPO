package com.bft.bo;

import java.util.Locale;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "orientations")

public class Orientations {

	@DatabaseField (id = true)
	private Integer id_orientation;
	@DatabaseField
	private String libelle;
	@DatabaseField
	private String libelle_court_en;
	@DatabaseField
	private String libelle_court_fr;
	
	public Orientations() {
		super();
	}

	public Orientations(Integer id_orientation, String libelle,
			String libelle_court_en,String libelle_court_fr) {
		super();
		this.id_orientation = id_orientation;
		this.libelle = libelle;
		this.libelle_court_en = libelle_court_en;
		this.libelle_court_fr = libelle_court_fr;

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

	public String getLibelle_court_en() {
		return libelle_court_en;
	}

	public void setLibelle_court_en(String libelle_court_en) {
		this.libelle_court_en = libelle_court_en;
	}	

	public String getLibelle_court_fr() {
		return libelle_court_fr;
	}

	public void setLibelle_court_fr(String libelle_court_fr) {
		this.libelle_court_fr = libelle_court_fr;
	}	
	
	public String toString() {
		if (Locale.getDefault().getISO3Language().equals("fra"))
			return libelle_court_fr;
		else
			return libelle_court_en;
	}
}