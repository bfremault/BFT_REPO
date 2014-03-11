package com.bft.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sail")
public class Sail {

	@DatabaseField (id = true)
	private Integer id_voile;
	@DatabaseField
	private String marque;
	@DatabaseField
	private String modele;
	@DatabaseField
	private float surface;
	@DatabaseField
	private String image;
	@DatabaseField
	private Integer annee;
	@DatabaseField
	private Integer acquisition;
	@DatabaseField
	private String commentaire;

	public Sail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sail(Integer id_voile, String marque, String modele, float surface,
			String image, Integer annee, Integer acquisition, String commentaire) {
		super();
		this.id_voile = id_voile;
		this.marque = marque;
		this.modele = modele;
		this.surface = surface;
		this.image = image;
		this.annee = annee;
		this.acquisition = acquisition;
		this.commentaire = commentaire;
	}

	public Integer getId_voile() {
		return id_voile;
	}

	public void setId_voile(Integer id_voile) {
		this.id_voile = id_voile;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Float getSurface() {
		return surface;
	}

	public void setSurface(Float surface) {
		this.surface = surface;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(Integer acquisition) {
		this.acquisition = acquisition;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String toString() {
		return marque+" "+modele+" "+surface;
	}
}
