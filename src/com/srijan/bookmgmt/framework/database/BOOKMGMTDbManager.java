package com.srijan.bookmgmt.framework.database;

import org.hibernate.Session;

import com.srijan.bookmgmt.framework.log.Log;

public class BOOKMGMTDbManager {
	
	private Session session = null;
	
	public Session createSession(){ 
		if(session == null){
			session = BOOKMGMTDatabaseResource.createSessionFactory().openSession();			 
			session.beginTransaction();
		}
		
		return session;
	}
	
	public void close(){
		if(session != null){
			
			try{
				session.getTransaction().commit();
			}catch(Exception exp){
				Log.debug("Exception while transaction commit for the session.");
				Log.debug("Caused by: "+exp.getLocalizedMessage());
			}
			
			try{
				session.flush();
			}catch(Exception exp){
				Log.debug("Exception while flushing the session.");
				Log.debug("Caused by: "+exp.getLocalizedMessage());
			}

			try{
				session.clear();
			}catch(Exception exp){
				Log.debug("Exception while clearing the session.");
				Log.debug("Caused by: "+exp.getLocalizedMessage());
			}

			try{
				session.close();
			}catch(Exception exp){
				Log.debug("Exception while closing the session.");
				Log.debug("Caused by: "+exp.getLocalizedMessage());
			}

			session = null;
		}
	}

}