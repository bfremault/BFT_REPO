package com.bft.bo;

import java.util.List;
import java.util.Map;

public class Data {

	private String user_session;
	private User user;
	//private List<Type_navigation> types_navigation;
	private int srvtime;
	private List<Sail> voiles;
	private List<Board> planches;
	private List<Session> sessions;
	private List<Spin> ailerons;
	private List<Mast> mats;
	//private spots_favoris; Liste d'objets spots_favoris
	//private Map<String,String> programmesLibelle; Pas parsé
	//orientations Liste d'objets orientations
	//liste_spots_supp; liste clef / valeurs
	private List<Spot> liste_spots;
	//liste_spots; Liste d'objets spots
	//liste_sessions_supp; ???
	private List<Pays> liste_pays;
	//Autre matos;??
			
	
	public Data() {
		super();
	}
	
	public Data(String user_session,
			User user,
			int srvtime,
			List<Board> planches,
			List<Sail> voiles,
			List<Mast> mats,
			List<Spin> ailerons,
			//List<Type_navigation> types_navigation,
			Map<String,String> programmesLibelle,
			List<Spot> liste_spots,
			List<Session> sessions,
			List<Pays> liste_pays) {
		super();
		this.user_session = user_session;
		this.user = user;
		this.srvtime = srvtime;
		this.planches = planches;
		this.voiles = voiles;
		this.ailerons = ailerons;
		this.mats = mats;
		//this.types_navigation = types_navigation;
		//this.programmesLibelle = programmesLibelle;
		this.liste_spots = liste_spots;
		this.sessions = sessions;
		this.liste_pays=liste_pays;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUser_session() {
		return user_session;
	}

	public void setUser_session(String user_session) {
		this.user_session = user_session;
	}

	public int getSrvtime() {
		return srvtime;
	}

	public void setSrvtime(int srvtime) {
		this.srvtime = srvtime;
	}

	public List<Board> getPlanches() {
		return planches;
	}

	public void setPlanches(List<Board> planches) {
		this.planches = planches;
	}
	
	public List<Sail> getVoiles() {
		return voiles;
	}

	public void setVoiles(List<Sail> voiles) {
		this.voiles = voiles;
	}
	
	public List<Spin> getAilerons() {
		return ailerons;
	}

	public void setAilerons(List<Spin> ailerons) {
		this.ailerons = ailerons;
	}

	public List<Mast> getMats() {
		return mats;
	}

	public void setMats(List<Mast> mats) {
		this.mats = mats;
	}

	/*	public Map<String, String> getProgrammesLibelle() {
		return programmesLibelle;
	}

	public void setProgrammesLibelle(Map<String, String> programmesLibelle) {
		this.programmesLibelle = programmesLibelle;
	}

	public List<Type_navigation> getTypes_navigation() {
		return types_navigation;
	}

	public void setTypes_navigation(List<Type_navigation> types_navigation) {
		this.types_navigation = types_navigation;
	}
*/
	public List<Spot> getListe_spots() {
		return liste_spots;
	}

	public void setListe_spots(List<Spot> liste_spots) {
		this.liste_spots = liste_spots;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	public List<Pays> getListe_pays() {
		return liste_pays;
	}

	public void setListe_pays(List<Pays> liste_pays) {
		this.liste_pays = liste_pays;
	}
}
