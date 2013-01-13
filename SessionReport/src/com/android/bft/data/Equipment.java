package com.android.bft.data;

public class Equipment {

	private int id;
	private String equipment;
	private int equipment_type;
	
	
	public Equipment() {}
	
	public Equipment(int id ,String equipment,int equipment_type){
		super();
		this.id=id;
		this.equipment=equipment;
		this.equipment_type=equipment_type;
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
	
}
