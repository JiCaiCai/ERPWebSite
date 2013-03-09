package com.erp.hibernate.beans;

public class ManufacturingUnit{
	private String ID;
	private String name;
	
	public ManufacturingUnit(){		
	}
	
	public ManufacturingUnit(String ID,String name){
		this.ID=ID;
		this.name=name;
	}

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
}
