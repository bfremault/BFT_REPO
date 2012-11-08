package com.android.bft.media;

public class PictureSession {
	private int id;
	private String picture_uri;
	private int id_session;
	
	public PictureSession(){}
	 
	public PictureSession(String picture_uri, int id_session){
		this.picture_uri = picture_uri;
		this.id_session = id_session;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicture_uri() {
		return picture_uri;
	}
	public void setPicture_uri(String picture_uri) {
		this.picture_uri = picture_uri;
	}
	public int getId_session() {
		return id_session;
	}
	public void setId_session(int id_session) {
		this.id_session = id_session;
	}
	
}
