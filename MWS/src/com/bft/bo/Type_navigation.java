package com.bft.bo;

public class Type_navigation {

	private String code;
	private String img;
	private String libelle;
	private String type_nav;
	
	public Type_navigation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Type_navigation(String code, String img, String libelle,
			String type_nav) {
		super();
		this.code = code;
		this.img = img;
		this.libelle = libelle;
		this.type_nav = type_nav;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getType_nav() {
		return type_nav;
	}
	public void setType_nav(String type_nav) {
		this.type_nav = type_nav;
	}	
}
