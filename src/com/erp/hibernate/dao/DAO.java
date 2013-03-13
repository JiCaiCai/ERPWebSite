package com.erp.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.erp.hibernate.beans.Material;
import com.erp.hibernate.beans.Stock;
import com.erp.hibernate.util.HibernateSessionFactory;

public class DAO{
	private static DAO dao=new DAO();
	private SessionFactory sessionFactory;
	
	private DAO(){
		
	}
	
//	public List Query(String hql){
//		Session session=null;
//		Transaction transaction=null;
//		List list=null;
//		try{
//			session=sessionFactory.getCurrentSession();
//			transaction=session.beginTransaction();
//			org.hibernate.Query query=session.createQuery(hql);
//			list=query.list();
//			query=null;
//			transaction.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			transaction.rollback();
//		}
//		return list;
//	}
	
	public static List Query(String hql){
		Session session=null;
		Transaction transaction=null;
		List list=null;
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			org.hibernate.Query query=session.createQuery(hql);
			list=query.list();
			query=null;
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}
		return list;
	}

	public static boolean Delete(String hql,String[] wildcard){
		Session session=null;
		Transaction transaction=null;
		boolean isSuccess=true;
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			org.hibernate.Query query=session.createQuery(hql);
			for(int i=0;i<wildcard.length;i++)
				query.setString(i,wildcard[i]);
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

	public static boolean Update(String hql,String[] wildcard){
		Session session=null;
		Transaction transaction=null;
		boolean isSuccess=true;
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			org.hibernate.Query query=session.createQuery(hql);
			for(int i=0;i<wildcard.length;i++)
				query.setString(i,wildcard[i]);
			transaction.commit();

		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess=false;
		}
		return isSuccess;
	}
	
	public static DAO getInstance(){
		return dao;
	}

	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
}
