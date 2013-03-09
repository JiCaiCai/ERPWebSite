package com.erp.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.erp.hibernate.beans.Level;
import com.erp.hibernate.beans.ManufacturingUnit;
import com.erp.hibernate.util.HibernateSessionFactory;

public class LevelDAO{
	public List Query(){
		return DAO.Query("select l.code,l.level from Level l");
	}
	
	public boolean Delete(String code){
		String[] wildcard={code};
		return DAO.Delete("delete from Level where code=?",wildcard);
	}
	
	public boolean Update(String code,String level){
		String[] wildcard={level,code};
		return DAO.Delete("update Level set level=? where code=?",wildcard);
	}
	
	public boolean Insert(Level level){
		Session session=null;
		Transaction transaction=null;
		boolean isSuccess=true;
		try{
			session=HibernateSessionFactory.getSession();
			transaction=session.beginTransaction();
			session.save(level);
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess=false;
		}
		return isSuccess;
	}
}
