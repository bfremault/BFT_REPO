package com.bft.bo;

import java.util.Map;

public class Spot {
	
	//private Map<String,String> commentaires;
	private Integer id_spot;
	private Integer id_pays;
	private Float latitude;
	private Float longitude;
	private Map<String,String> noteNavigation;
	private String orientation;
	//private String photosSpot; Liste image_path et titre
	private Map<String,String> programmesNavigation;
	private String region;
	private String spot;
	private String ville;
		
	public Spot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Spot(
			//Map<String, String> commentaires, 
			Integer id_pays, Integer id_spot,
			Float latitude, Float longitude, Map<String,String> noteNavigation,
			String orientation, 
			Map<String, String> programmesNavigation, String region,
			String spot, String ville) {
		super();
		//this.commentaires = commentaires;
		this.id_pays = id_pays;
		this.id_spot = id_spot;
		this.latitude = latitude;
		this.longitude = longitude;
		this.noteNavigation = noteNavigation;
		this.orientation = orientation;
		//this.photosSpot = photosSpot;
		this.programmesNavigation = programmesNavigation;
		this.region = region;
		this.spot = spot;
		this.ville = ville;
	}
/*	public Map<String, String> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(Map<String, String> commentaires) {
		this.commentaires = commentaires;
	}*/
	public Integer getId_pays() {
		return id_pays;
	}
	public void setId_pays(Integer id_pays) {
		this.id_pays = id_pays;
	}
	public Integer getId_spot() {
		return id_spot;
	}
	public void setId_spot(Integer id_spot) {
		this.id_spot = id_spot;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Map<String,String> getNoteNavigation() {
		return noteNavigation;
	}
	public void setNoteNavigation(Map<String,String> noteNavigation) {
		this.noteNavigation = noteNavigation;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
/*	public String getPhotosSpot() {
		return photosSpot;
	}
	public void setPhotosSpot(String photosSpot) {
		this.photosSpot = photosSpot;
	}*/
	public Map<String, String> getProgrammesNavigation() {
		return programmesNavigation;
	}
	public void setProgrammesNavigation(Map<String, String> programmesNavigation) {
		this.programmesNavigation = programmesNavigation;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSpot() {
		return spot;
	}
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
}
