package com.bft.bo;

public class PhotosSpot implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5257196781661185750L;
	
	private String image_path;
	private String titre;
		
	public PhotosSpot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhotosSpot(String image_path, String titre) {
		super();
		this.image_path = image_path;
		this.titre = titre;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	
	
}
