package com.bft.bo;

import java.io.Serializable;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "session")
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5061140275193335256L;

	
	@DatabaseField (id = true)
	private Integer id_session;
	@DatabaseField
	private Long date;
	@DatabaseField
	private Integer id_spot;
	@DatabaseField
	private Integer ventMin;
	@DatabaseField
	private Integer ventMax;
	@DatabaseField
	private Integer id_orientation;
/*	@ForeignCollectionField(eager = false)
	List<Board> id_planche;*/
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private Integer[] id_planche;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private Integer[] id_voile;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private Integer[] id_aileron;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private Integer[] id_mat;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private Integer[] id_divers;
	@DatabaseField
	private Integer note;
	@DatabaseField
	private String commentaire;
	@DatabaseField
	private Integer vague;
	@DatabaseField
	private Integer duree;
	@DatabaseField
	private Integer vmax;
	@DatabaseField
	private Integer distance;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private String[] photos_session;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private Integer[] all_types_nav;
	
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Session(Integer id_session,
			Long date,
			Integer id_spot,
			Integer ventMin,
			Integer ventMax,
			Integer id_orientation,
			Integer[] id_planche,
			Integer[] id_voile,
			Integer[] id_aileron,
			Integer[] id_mat,
			Integer[] id_divers,
			Integer note, String commentaire,
			Integer vague,
			Integer duree,
			Integer vmax,
			Integer distance,
			String[] photos_session,
			Integer[] all_types_nav) {
		super();
		this.id_session = id_session;
		this.date = date;
		this.id_spot = id_spot;
		this.ventMin = ventMin;
		this.ventMax = ventMax;
		this.id_orientation = id_orientation;
		this.id_planche = id_planche;
		this.id_voile = id_voile;
		this.id_aileron = id_aileron;
		this.id_mat = id_mat;
		this.id_divers = id_divers;
		this.note = note;
		this.commentaire = commentaire;
		this.vague = vague;
		this.duree = duree;
		this.vmax = vmax;
		this.distance = distance;
		this.photos_session = photos_session;
		this.all_types_nav = all_types_nav;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Integer getId_spot() {
		return id_spot;
	}

	public void setId_spot(Integer id_spot) {
		this.id_spot = id_spot;
	}

	public Integer getVentMin() {
		return ventMin;
	}

	public void setVentMin(Integer ventMin) {
		this.ventMin = ventMin;
	}

	public Integer getVentMax() {
		return ventMax;
	}

	public void setVentMax(Integer ventMax) {
		this.ventMax = ventMax;
	}

	public Integer getId_orientation() {
		return id_orientation;
	}

	public void setId_orientation(Integer id_orientation) {
		this.id_orientation = id_orientation;
	}

	public Integer[] getId_planche() {
		return id_planche;
	}

	public void setId_planche(Integer[] id_planche) {
		this.id_planche = id_planche;
	}

	public Integer[] getId_voile() {
		return id_voile;
	}

	public void setId_voile(Integer[] id_voile) {
		this.id_voile = id_voile;
	}

	public Integer[] getId_aileron() {
		return id_aileron;
	}

	public void setId_aileron(Integer[] id_aileron) {
		this.id_aileron = id_aileron;
	}

	public Integer[] getId_mat() {
		return id_mat;
	}

	public void setId_mat(Integer[] id_mat) {
		this.id_mat = id_mat;
	}

	public Integer[] getId_divers() {
		return id_divers;
	}

	public void setId_divers(Integer[] id_divers) {
		this.id_divers = id_divers;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Integer getVague() {
		return vague;
	}

	public void setVague(Integer vague) {
		this.vague = vague;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Integer getVmax() {
		return vmax;
	}

	public void setVmax(Integer vmax) {
		this.vmax = vmax;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String[] getPhotos_session() {
		return photos_session;
	}

	public void setPhotos_session(String[] photos_session) {
		this.photos_session = photos_session;
	}

	public Integer[] getAll_types_nav() {
		return all_types_nav;
	}

	public void setAll_types_nav(Integer[] all_types_nav) {
		this.all_types_nav = all_types_nav;
	}

}
