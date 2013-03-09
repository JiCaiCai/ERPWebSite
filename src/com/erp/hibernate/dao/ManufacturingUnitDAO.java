package com.erp.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.erp.hibernate.beans.ManufacturingUnit;
import com.erp.hibernate.util.HibernateSessionFactory;

public class ManufacturingUnitDAO{
	public List Query(){
		return DAO.Query("select m.ID,m.name from ManufacturingUnit m");
	}
	
	public boolean Delete(String ID){
		String[] wildcard={ID};
		return DAO.Delete("delete from ManufacturingUnit where ID=?",wildcard);
	}
	
	public boolean Update(String ID,String name){
		String[] wildcard={name,ID};
		return DAO.Delete("update ManufacturingUnit set name=? where ID=?",wildcard);
	}
	
	public boolean Insert(ManufacturingUnit mu){
		Session session=null;
		Transaction transaction=null;
		boolean isSuccess=true;
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			session.save(mu);
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess=false;
		}
		return isSuccess;
	}
	
	public static void main(String[] args){
		ManufacturingUnitDAO service=new ManufacturingUnitDAO();
		service.Query();
	}
}
