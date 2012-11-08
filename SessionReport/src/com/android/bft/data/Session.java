package com.android.bft.data;

import java.util.Date;

public class Session {


	private int id;
	private String location;
	private float rate;
	private Date date;
	private String sail;
	private String board;
	private String comment;
	private String windDirection;
	private String windPower;
	
	public Session(){}
	 
	public Session(String location, float rate, Date date, String sail,
			String board, String comment, String windDirection, String windPower) {
		super();
		this.id = id;
		this.location = location;
		this.rate = rate;
		this.date = date;
		this.sail = sail;
		this.board = board;
		this.comment = comment;
		this.windDirection = windDirection;
		this.windPower = windPower;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSail() {
		return sail;
	}

	public void setSail(String sail) {
		this.sail = sail;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindPower() {
		return windPower;
	}

	public void setWindPower(String windPower) {
		this.windPower = windPower;
	}
	
}
