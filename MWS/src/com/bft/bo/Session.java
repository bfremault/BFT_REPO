package com.bft.bo;

public class Session {

	private Integer id_session;
	private Integer date;
	private Integer id_spot;
	private Integer ventMin;
	private Integer ventMax;
	private Integer id_orientation;
	private Integer[] id_planche;
	private Integer[] id_voile;
	private Integer[] id_aileron;
	private Integer[] id_mat;
	private Integer[] id_divers;
	private Integer note;
	private String commentaire;
	private Integer vague;
	private Integer duree;
	private Integer vmax;
	private Integer distance;
	private String photos_session;
	private Integer[] all_types_nav;
	
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Session(Integer id_session, Integer date, Integer id_spot,
			Integer ventMin, Integer ventMax, Integer id_orientation,
			Integer[] id_planche,
			Integer[] id_voile, Integer[] id_aileron,
			Integer[] id_mat, Integer[] id_divers,
			Integer note, String commentaire, Integer vague, Integer duree, Integer vmax, Integer distance,
			String photos_session,Integer[] all_types_nav) {
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

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
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

	public String getPhotos_session() {
		return photos_session;
	}

	public void setPhotos_session(String photos_session) {
		this.photos_session = photos_session;
	}

	public Integer[] getAll_types_nav() {
		return all_types_nav;
	}

	public void setAll_types_nav(Integer[] all_types_nav) {
		this.all_types_nav = all_types_nav;
	}
	
	

}
