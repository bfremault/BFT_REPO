package com.bft.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "spin")
public class Spin {
	@DatabaseField (id = true)
	private Integer id_aileron;
	@DatabaseField
	private String marque;
	@DatabaseField
	private String modele;
	@DatabaseField
	private float taille;
	@DatabaseField
	private String image;
	@DatabaseField
	private Integer annee;
	@DatabaseField
	private Integer acquisition;
	@DatabaseField
	private String commentaire;
	
	public Spin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Spin(Integer id_aileron, String marque, String modele, float taille,
			String image, Integer annee, Integer acquisition, String commentaire) {
		super();
		this.id_aileron = id_aileron;
		this.marque = marque;
		this.modele = modele;
		this.taille = taille;
		this.image = image;
		this.annee = annee;
		this.acquisition = acquisition;
		this.commentaire = commentaire;
	}

	public Integer getId_aileron() {
		return id_aileron;
	}

	public void setId_aileron(Integer id_aileron) {
		this.id_aileron = id_aileron;
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

	public float getTaille() {
		return taille;
	}

	public void setTaille(float taille) {
		this.taille = taille;
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
		return marque+" "+modele+" "+taille;
	}
}
