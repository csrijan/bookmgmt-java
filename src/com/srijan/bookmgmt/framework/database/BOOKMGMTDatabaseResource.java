/**
 * 
 */
package com.srijan.bookmgmt.framework.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.srijan.bookmgmt.framework.log.Log;

/**
 * 
 * This class is responsible for loading the Hibernate connection
 * provided in the Hibernate configuration file and create a session 
 * factory. The client code which intend to load session factory
 * should call "createSessionFactory" method of this class.
 *
 */
public class BOOKMGMTDatabaseResource {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
		
	private static void loadSessionFactory(){
		Log.debug("Initializing database connectivity.");

		Configuration configuration = new Configuration();
		configuration.configure();

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		Log.debug("Done initializing database connectivity.");
	}
	
	public static SessionFactory createSessionFactory(){ 
		Log.debug("Initializing database connectivity.");
		if(sessionFactory == null){
			loadSessionFactory();
		}
	    return sessionFactory;
	}

}