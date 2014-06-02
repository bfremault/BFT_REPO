package com.bft.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pays")

public class Pays {
	
	@DatabaseField (id = true)
	private Integer id_pays;
	@DatabaseField
	private String code;
	@DatabaseField
	private String drapeau;
	@DatabaseField
	private String pays;
	
	public Pays() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pays(String code, String drapeau, Integer id_pays, String pays) {
		super();
		this.code = code;
		this.drapeau = drapeau;
		this.id_pays = id_pays;
		this.pays = pays;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDrapeau() {
		return drapeau;
	}
	public void setDrapeau(String drapeau) {
		this.drapeau = drapeau;
	}
	public Integer getId_pays() {
		return id_pays;
	}
	public void setId_pays(Integer id_pays) {
		this.id_pays = id_pays;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
}
