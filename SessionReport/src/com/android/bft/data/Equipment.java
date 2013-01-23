package com.android.bft.data;

public class Equipment {
	
	private int id;
	private String equipment;
	private float equipment_volume;
	private int equipment_type;
	private boolean equipment_archive;
	
	
	public Equipment() {}
	
	public Equipment(int id ,String equipment,float equipment_volume,int equipment_type,boolean equipment_archive){
		super();
		this.id=id;
		this.equipment=equipment;
		this.equipment_volume=equipment_volume;
		this.equipment_type=equipment_type;
		this.equipment_archive=equipment_archive;
	}
	
		
	public int getEquipment_type() {
		return equipment_type;
	}
	public void setEquipment_type(int equipment_type) {
		this.equipment_type = equipment_type;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getEquipment_volume() {
		return equipment_volume;
	}

	public void setEquipment_volume(float equipment_volume) {
		this.equipment_volume = equipment_volume;
	}

	public boolean isEquipment_archive() {
		return equipment_archive;
	}

	public void setEquipment_archive(boolean equipment_archive) {
		this.equipment_archive = equipment_archive;
	}
	
}
