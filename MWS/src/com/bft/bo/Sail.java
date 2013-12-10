package com.bft.bo;

public class Sail {

	private Integer id_voile;
	private String marque;
	private String modele;
	private String surface;
	private String image;
	private Integer annee;
	private String acquisition;
	private String commentaire;

	public Sail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sail(Integer id_voile, String marque, String modele, String surface,
			String image, Integer annee, String acquisition, String commentaire) {
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

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
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

	public String getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(String acquisition) {
		this.acquisition = acquisition;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}	
}
