package com.bft.bo;

public class Commentaires implements java.io.Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -691163245512240707L;
	
	private String commentaire;
	private Integer date;
	private String id_rider;
	private String image_profil;
	private String rider;
	
	public Commentaires() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commentaires(String commentaire, Integer date, String id_rider,
			String image_profil, String rider) {
		super();
		this.commentaire = commentaire;
		this.date = date;
		this.id_rider = id_rider;
		this.image_profil = image_profil;
		this.rider = rider;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public String getId_rider() {
		return id_rider;
	}
	public void setId_rider(String id_rider) {
		this.id_rider = id_rider;
	}
	public String getImage_profil() {
		return image_profil;
	}
	public void setImage_profil(String image_profil) {
		this.image_profil = image_profil;
	}
	public String getRider() {
		return rider;
	}
	public void setRider(String rider) {
		this.rider = rider;
	}
}
