package com.erp.hibernate.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.erp.hibernate.beans.Material;
import com.erp.hibernate.beans.Stock;
import com.erp.hibernate.util.HibernateSessionFactory;

public class StockDAO{
	public List<Stock> Query(){
		Session session=null;
		Transaction transaction=null;
		List<Stock> stocks=null;
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			org.hibernate.Query query=session.createQuery("select s.materialID,m.name,s.stockNum from Stock s,Material m where s.materialID=m.ID");
			stocks=(List<Stock>)query.list();
			query=null;
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}
		return stocks;
	}
	
	public boolean Delete(String id){
		Session session=null;
		Transaction transaction=null;
		boolean isSuccess=true;
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			org.hibernate.Query query=session.createQuery("delete Stock s where s.materialID=?");
			query.setString(0,id);
			query.executeUpdate();
			query=null;
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess=false;
		}
		return isSuccess;
	}
	
	public boolean Insert(Stock stock){
		Session session=null;
		Transaction transaction=null;
		boolean isSuccess=true;
		MaterialDAO service=new MaterialDAO();
		Material material=service.Valid(stock.getMaterialID());
		stock.setMaterial(material);
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			session.save(stock);
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess=false;
		}
		return isSuccess;
	}
	
	public static void main(String[] args){
		StockDAO stockService=new StockDAO();
		MaterialDAO materialService=new MaterialDAO();
		Material material=materialService.Valid("99");
		Stock stock=new Stock("99",2000);
		stock.setMaterial(material);
		stockService.Insert(stock);
	}
}
