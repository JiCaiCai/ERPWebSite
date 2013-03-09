package com.erp.hibernate.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.erp.hibernate.beans.Material;
import com.erp.hibernate.beans.User;
import com.erp.hibernate.util.HibernateSessionFactory;

public class MaterialDAO{
	public Material Valid(String ID){
		Session session=null;
		Transaction transaction=null;
		Material material=null;
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			Query query=session.createQuery("from Material where ID=?");
			query.setString(0,ID.trim());
			material=(Material)query.uniqueResult();
			query=null;
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
			transaction.rollback();
		} 
		HibernateSessionFactory.closeSession();
		return material;
	}

	public boolean IsExist(String Name){
		Session session=null;
		Transaction transaction=null;
		boolean isExist=false;
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			Query query= session.createQuery("from Material where name=?");
			query.setString(0, Name.trim());
			List<Material> materials=query.list();
			if (materials!=null && materials.size()>0)
				isExist=true;
			query=null;
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
			transaction.rollback();
		} 
		return isExist;
	}
	
	public List Query(){
		return DAO.Query("select m.ID,m.name,m.specification,r.name from Material m,Route r where m.routeID=r.ID");
	}
	
	public boolean Delete(String ID){
		String[] wildcard={ID};
		return DAO.Delete("delete from Material where ID=?",wildcard);
	}
	
	public boolean Update(String ID,String name,String specification,String routeID){
		String[] wildcard={name,specification,routeID};
		return DAO.Update("update Material set name=?,specification=?,routeID=? where ID=?",wildcard);
	}
	
	public static void main(String[] args) {
		String[] wildcard={"2","3","4"};
		MaterialDAO service = new MaterialDAO();
		Material material = service.Valid("0");
		System.out.println(service.IsExist("物料0"));
		System.out.println(service.IsExist("物料99"));
	}
}
