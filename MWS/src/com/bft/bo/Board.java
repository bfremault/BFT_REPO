package com.bft.bo;

import java.io.Serializable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "board")
public class Board implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4360481044316372498L;
	
	
	@DatabaseField (id = true)
	private Integer id_planche;
	@DatabaseField
	private String marque;
	@DatabaseField
	private String modele;
	@DatabaseField
	private Integer volume;
	@DatabaseField
	private String image;
	@DatabaseField
	private Integer annee;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private Integer[] programme;
	@DatabaseField
	private Integer acquisition;
	@DatabaseField
	private String commentaire;

	private float zero = 0;

	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(Integer id_planche,
			String marque,
			String modele,
			Integer volume,
			String image,
			Integer annee,
			Integer acquisition,
			Integer[] programme,
			String commentaire) {
		super();
		this.id_planche = id_planche;
		this.marque = marque;
		this.modele = modele;
		this.volume = volume;
		this.image = image;
		this.annee = annee;
		this.setProgramme(programme);
		this.acquisition = acquisition;
		this.commentaire = commentaire;
	}

	public Integer getId_planche() {
		return id_planche;
	}

	public void setId_planche(Integer id_planche) {
		this.id_planche = id_planche;
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

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
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

	public Integer[] getProgramme() {
		return programme;
	}

	public void setProgramme(Integer[] programme) {
		this.programme = programme;
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
		return marque+" "+modele;
	}
		
}
