package com.bft.utils;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private String user_session;
	private User user;
	//private types_navigation; "code" : "W","img" : "W",  "libelle" : "Windsurf",  "type_nav" : "20"
	private int srvtime;
	private Voiles voiles;
	//private sessions; Liste d'objets sessions
	//private spots_favoris; Liste d'objets spots_favoris
	//private programmesLibelle; liste clef / valeurs
	//orientations Liste d'objets orientations
	//private mats; Liste d'objets mats
	//private planches; Liste d'objets planches
	//private voiles; Liste d'objets voiles
	//ailerons	
	//liste_spots_supp; liste clef / valeurs
	
	//liste_spots; Liste d'objets spots
	//liste_sessions_supp; ???
	//liste_pays; Liste d'objets pays
	//Autre matos;??
			
	
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data(String user_session, User user, int srvtime, Voiles voiles) {
		super();
		this.user_session = user_session;
		this.user = user;
		this.srvtime = srvtime;
		this.voiles = voiles;
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

	public Voiles getSails() {
		return voiles;
	}

	public void setSails(Voiles voiles) {
		this.voiles = voiles;
	}
	
}
