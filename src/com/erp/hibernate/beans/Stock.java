package com.erp.hibernate.beans;

public class Stock{
	private String materialID;
	private int stockNum;
	private Material material;
	
	public Stock(){
		
	}
	
	public Stock(String materialID,int stockNum){
		this.materialID=materialID;
		this.stockNum=stockNum;
	}
	
	public String getMaterialID(){
		return materialID;
	}
	public void setMaterialID(String materialID){
		this.materialID=materialID;
	}
	public int getStockNum(){
		return stockNum;
	}
	public void setStockNum(int stockNum){
		this.stockNum=stockNum;
	}

	public Material getMaterial(){
		return material;
	}

	public void setMaterial(Material material){
		this.material=material;
	}
}
