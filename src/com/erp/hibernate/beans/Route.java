package com.erp.hibernate.beans;

public class Route{
	private String ID;
	private String name;
	private String manufacturingID;
	private String workCenterID;
	private int leadTime;
	private float attritionRate;

	public String getID(){
		return ID;
	}

	public void setID(String iD){
		ID=iD;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getManufacturingID(){
		return manufacturingID;
	}

	public void setManufacturingID(String manufacturingID){
		this.manufacturingID=manufacturingID;
	}

	public String getWorkCenterID(){
		return workCenterID;
	}

	public void setWorkCenterID(String workCenterID){
		this.workCenterID=workCenterID;
	}

	public int getLeadTime(){
		return leadTime;
	}

	public void setLeadTime(int leadTime){
		this.leadTime=leadTime;
	}

	public float getAttritionRate(){
		return attritionRate;
	}

	public void setAttritionRate(float attritionRate){
		this.attritionRate=attritionRate;
	}
}
