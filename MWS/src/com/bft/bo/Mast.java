package com.bft.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "mast")
public class Mast {
	@DatabaseField (id = true)
	private Integer id_mat;
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
	
	private float zero = 0;
	
	public Mast() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mast(Integer id_mat, String marque, String modele, float taille,
			String image, Integer annee, Integer acquisition, String commentaire) {
		super();
		this.id_mat = id_mat;
		this.marque = marque;
		this.modele = modele;
		this.taille = taille;
		this.image = image;
		this.annee = annee;
		this.acquisition = acquisition;
		this.commentaire = commentaire;
	}

	public Integer getId_mat() {
		return id_mat;
	}

	public void setId_mat(Integer id_mat) {
		this.id_mat = id_mat;
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
		
		if (java.lang.Float.compare(taille, zero) == 0){
			return marque+" "+modele;						
		}else
		{
			return marque+" "+modele+ " "+ taille/100;
		}		
	}
}
