package com.erp.hibernate.beans;

public class Material{
	private String ID;
	private String name;
	private String specification;
	private String routeID;
	private Stock stock;

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
	public String getSpecification(){
		return specification;
	}
	public void setSpecification(String specification){
		this.specification=specification;
	}
	public String getRouteID(){
		return routeID;
	}
	public void setRouteID(String routeID){
		this.routeID=routeID;
	}
	public Stock getStock(){
		return stock;
	}
	public void setStock(Stock stock){
		this.stock=stock;
	}
}
