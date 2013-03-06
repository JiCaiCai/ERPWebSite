package com.erp.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.erp.hibernate.beans.User;
import com.erp.hibernate.util.HibernateSessionFactory;

public class UserDAO{

	public User Valid(String userName, String password) throws HibernateException{
		Session session=null;
		Transaction transaction=null;
		User user=null;
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			Query query=session.createQuery("from User where userName=? and password=?");
			query.setString(0,userName.trim());
			query.setString(1,password.trim());
			user=(User)query.uniqueResult();
			query=null;
			transaction.commit();
		} catch (HibernateException e){
			transaction.rollback();
			throw e;
		} 
		HibernateSessionFactory.closeSession();
		return user;
	}

	public boolean IsExist(String userName) throws HibernateException{
		Session session=null;
		Transaction transaction=null;
		boolean isExist=false;
		try {
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			Query query= session.createQuery("from User where userName=?");
			query.setString(0, userName.trim());
			User user=(User) query.uniqueResult();
			if (user!=null)
				isExist=true;
			query=null;
			transaction.commit();
		} catch (HibernateException e){
			transaction.rollback();
			throw e;
		} 
		return isExist;
	}

	public boolean Add(String userName, String password, String email) throws HibernateException {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = true;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);		
			session.save(user);
			transaction.commit();
		} catch (HibernateException e) {
			isSuccess = false;
			transaction.rollback();
			throw e;
		} 
		return isSuccess;
	}

	public static void main(String[] args) {
		UserDAO service = new UserDAO();
		User user = service.Valid("admin", "admin");
		System.out.println(service.IsExist("admin"));
		System.out.println(service.IsExist("ddd"));
	}

}
