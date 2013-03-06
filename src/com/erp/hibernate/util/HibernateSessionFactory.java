package com.erp.hibernate.util;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
  
//import org.apache.log4j.Logger;  
import org.hibernate.HibernateException;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
  
/** 
 * Configures and provides access to Hibernate sessions, tied to the 
 * current thread of execution.  Follows the Thread Local Session 
 * pattern, see {@link http://hibernate.org/42.html }. 
 */  
public class HibernateSessionFactory {  
//    private  static Logger log = Logger.getLogger(HibernateSessionFactory.class);  
    /**  
     * Location of hibernate.cfg.xml file. 
     * Location should be on the classpath as Hibernate uses   
     * #resourceAsStream style lookup for its configuration file.  
     * The default classpath location of the hibernate config file is  
     * in the default package. Use #setConfigFile() to update  
     * the location of the configuration file for the current session.    
     */  
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";  
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();  
    private  static Configuration configuration = new Configuration();      
    private static SessionFactory sessionFactory;  
    private static String configFile = CONFIG_FILE_LOCATION;  
  
    static {  
        try {  
            configuration.configure(configFile);  
            sessionFactory = configuration.buildSessionFactory();  
        } catch (Exception e) {  
            System.err  
                    .println("%%%% Error Creating SessionFactory %%%%");  
            e.printStackTrace();  
        }  
    }  
    private HibernateSessionFactory() {  
    }  
      
    /** 
     * Returns the ThreadLocal Session instance.  Lazy initialize 
     * the <code>SessionFactory</code> if needed. 
     * 
     *  @return Session 
     *  @throws HibernateException 
     */  
    public static Session getSession() throws HibernateException {  
        Session session = (Session) threadLocal.get();  
  
        if (session == null || !session.isOpen()) {  
            if (sessionFactory == null) {  
                rebuildSessionFactory();  
            }  
            session = (sessionFactory != null) ? sessionFactory.openSession()  
                    : null;  
            threadLocal.set(session);  
        }  
  
        return session;  
    }  
  
    /** 
     *  Rebuild hibernate session factory 
     * 
     */  
    public static void rebuildSessionFactory() {  
        try {  
            configuration.configure(configFile);  
            sessionFactory = configuration.buildSessionFactory();  
        } catch (Exception e) {  
            System.err.println("%%%% Error Creating SessionFactory %%%%");  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     *  Close the single hibernate session instance. 
     * 
     *  @throws HibernateException 
     */  
    public static void closeSession() throws HibernateException {  
        Session session = (Session) threadLocal.get();  
        threadLocal.set(null);  
  
        if (session != null) {  
            session.close();  
        }  
    }  
  
    /** 
     *  return session factory 
     * 
     */  
    public static org.hibernate.SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
  
    /** 
     *  return session factory 
     * 
     *  session factory will be rebuilded in the next call 
     */  
    public static void setConfigFile(String configFile) {  
        HibernateSessionFactory.configFile = configFile;  
        sessionFactory = null;  
    }  
  
    /** 
     *  return hibernate configuration 
     * 
     */  
    public static Configuration getConfiguration() {  
        return configuration;  
    }  
      
    /** 
     * comment:begin the transaction 
     * @param session 
     */  
    public final static Transaction beginTransaction(Session session){  
        if(session!=null){            
        return session.beginTransaction();  
        }else{  
//            log.error("CIGA exception in begin transaction: can not begin transaction!because session is null!");  
            return null;  
        }  
    }  
      
    /** 
     * comment:commit transaction 
     * @param tx 
     * @throws Exception  
     */  
    public final static void commitTransaction(Transaction tx){  
          
        if(tx!=null){  
            tx.commit();  
        }else{  
//            log.error("CIGA exception in commit transaction: transaction is null!!!!");  
            try{  
                throw new NullPointerException("CIGA exception in commit transaction: transaction is null!");  
            }catch(Exception e){  
                e.printStackTrace();  
            }         
        }                 
    }  
    /** 
     * roll back the transaction 
     * @param tx 
     */  
    public final static void rollBackTransaction(Transaction tx){         
        if (tx!=null) {  
            tx.rollback();  
        }else{  
//            log.error("CIGA exception in roback transaction: transaction is null!!!!");  
            try{  
                throw new NullPointerException("CIGA exception in rollback transaction: transaction is null!");  
            }catch(Exception e){  
                e.printStackTrace();  
            }         
        }  
    }  
    /**********************************************/  
    public static final String url = "jdbc:db2://10.20.0.185:50001/sx1";    
    public static final String username = "sxzmusr1";    
    public static final String password = "sxzmusr1";    
    public static final String driverClassName = "com.ibm.db2.jcc.DB2Driver";    
    public static Connection makeConnection() {    
        Connection conn = null;    
        try {    
            Class.forName(driverClassName);    
        } catch (ClassNotFoundException e) {    
            e.printStackTrace();    
        }    
        try {    
            conn = DriverManager.getConnection(url, username, password);    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
        return conn;    
    }    
  
}  
