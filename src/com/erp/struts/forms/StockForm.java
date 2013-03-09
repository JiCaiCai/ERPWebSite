package com.erp.struts.forms;

import org.apache.struts.action.ActionForm;

public class StockForm extends ActionForm{
	private static final long serialVersionUID=6417328299552754222L;
	private String materialID;
	private int stockNum;

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
}
