package com.bft.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
	
	@DatabaseField (id = true)
	private int id;
	@DatabaseField
	private String user_avatar;
	@DatabaseField
	private String user_pseudo;
	@DatabaseField
	private String user_challengers;
	@DatabaseField
	private String user_creation;
	@DatabaseField
	private String user_dist_max;
	@DatabaseField
	private String user_fans;
	@DatabaseField
	private String user_pays;
	@DatabaseField
	private String user_ville;
	@DatabaseField
	private String user_vmax;
	

	public User() {
		super();
	}
	
	public User(int id, String user_avatar, String user_pseudo,
			String user_challengers, String user_creation,
			String user_dist_max, String user_fans, String user_pays,
			String user_ville, String user_vmax) {
		super();
		this.id = id;
		this.user_avatar = user_avatar;
		this.user_pseudo = user_pseudo;
		this.user_challengers = user_challengers;
		this.user_creation = user_creation;
		this.user_dist_max = user_dist_max;
		this.user_fans = user_fans;
		this.user_pays = user_pays;
		this.user_ville = user_ville;
		this.user_vmax = user_vmax;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_avatar() {
		return user_avatar;
	}
	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}
	public String getUser_pseudo() {
		return user_pseudo;
	}
	public void setUser_pseudo(String user_pseudo) {
		this.user_pseudo = user_pseudo;
	}
	public String getUser_challengers() {
		return user_challengers;
	}

	public void setUser_challengers(String user_challengers) {
		this.user_challengers = user_challengers;
	}

	public String getUser_creation() {
		return user_creation;
	}

	public void setUser_creation(String user_creation) {
		this.user_creation = user_creation;
	}

	public String getUser_dist_max() {
		return user_dist_max;
	}

	public void setUser_dist_max(String user_dist_max) {
		this.user_dist_max = user_dist_max;
	}

	public String getUser_fans() {
		return user_fans;
	}

	public void setUser_fans(String user_fans) {
		this.user_fans = user_fans;
	}

	public String getUser_pays() {
		return user_pays;
	}

	public void setUser_pays(String user_pays) {
		this.user_pays = user_pays;
	}

	public String getUser_ville() {
		return user_ville;
	}

	public void setUser_ville(String user_ville) {
		this.user_ville = user_ville;
	}

	public String getUser_vmax() {
		return user_vmax;
	}

	public void setUser_vmax(String user_vmax) {
		this.user_vmax = user_vmax;
	}
}
