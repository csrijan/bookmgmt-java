package com.srijan.bookmgmt.framework.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.srijan.bookmgmt.framework.database.BOOKMGMTDbManager;
import com.srijan.bookmgmt.framework.log.Log;
import com.srijan.bookmgmt.pojo.AuthUserInputPojo;
import com.srijan.bookmgmt.pojo.AuthUserOutputPojo;
import com.srijan.bookmgmt.pojo.AuthUserOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListCommonInputPojo;
import com.srijan.bookmgmt.pojo.ListGenresOutputPojo;
import com.srijan.bookmgmt.pojo.ListGenresOutputSetPojo;

public class AuthServiceDao {
	
	public AuthUserOutputSetPojo authUser(AuthUserInputPojo inputData) {
		
		AuthUserOutputSetPojo outpojo = new AuthUserOutputSetPojo();
		Set<AuthUserOutputPojo> liout = null;
		AuthUserOutputPojo lione = null;
		
		List<Object> li = null;
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
		try
		{
			
			dbMngr = new BOOKMGMTDbManager();
			session = dbMngr.createSession();
			
			liout = new HashSet<AuthUserOutputPojo>();
			
			int userAuth=0;
			String sessionid="";
			
			String qr = "select username, 1 from bookmgmt.bm_user where username = :username and userpass = :userpass";
			Query query = session.createSQLQuery(qr);
			query.setParameter("username",inputData.getUsername());
			query.setParameter("userpass",inputData.getPassword());
			li = query.list();
			
			Object element[] = null;
			Iterator<Object> itr = li.iterator();			
			
			while(itr.hasNext())
			{
				userAuth=1;
				sessionid = UUID.randomUUID().toString();				
				lione = new AuthUserOutputPojo();				
				element = (Object[]) itr.next();
				lione.setUsername(element[0]+"");
				lione.setSessionid(sessionid);
				liout.add(lione);				
			}
			
			outpojo.setAuthUserOutput(liout);
			
			if(userAuth==1)
			{
				int res=0;
				
				String qr1 = "update bookmgmt.bm_user set sessionid = :sessionid where username = :username";
				Query query1 = session.createSQLQuery(qr1);
				query1.setParameter("sessionid",sessionid);
				query1.setParameter("username",inputData.getUsername());
				res = query1.executeUpdate();
				
				if(res!=1)
				{
					throw new Exception("failed to update session id");
				}
			}
			
		}
		catch (Exception e)
		{
			Log.debug("Error while authorizing user. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return outpojo;
		
	}
	
	public String addUser(AuthUserInputPojo inputData) {
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
		String ret="Failed";
		
		try
		{
			
			dbMngr = new BOOKMGMTDbManager();
			session = dbMngr.createSession();
			
			String qr = "insert into bookmgmt.bm_user(username, userpass) values (:username, :userpass)";
			Query query = session.createSQLQuery(qr);
			query.setParameter("username",inputData.getUsername());
			query.setParameter("userpass",inputData.getPassword());
			int res = query.executeUpdate();
			
			Log.debug("res "+res);
			
			if(res==1)
			{
				ret="Success";
			}
		}		
		catch (ConstraintViolationException e)
		{
			Log.debug("Error while adding user. (Primary Key Violation) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
			ret="Duplicate";
		}
		catch (Exception e)
		{
			Log.debug("Error while adding user. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return ret;
		
	}
	
	public String logoffUser(ListCommonInputPojo inputData) {
		
		List<Object> li1 = null;
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
		String ret="Failed";
		String auth="";
		
		try
		{
			
			dbMngr = new BOOKMGMTDbManager();
			session = dbMngr.createSession();
			
			Log.debug("username "+inputData.getUsername());
			Log.debug("sessionid "+inputData.getSessionid());
			
			String qr1 = "select 1, 2 from bookmgmt.bm_user where username = :username and sessionid = :sessionid";
			Query query1 = session.createSQLQuery(qr1);
			query1.setParameter("username",inputData.getUsername());
			query1.setParameter("sessionid",inputData.getSessionid());
			li1 = query1.list();
			
			Object element1[] = null;
			Iterator<Object> itr1 = li1.iterator();			
			
			while(itr1.hasNext())
			{			
				element1 = (Object[]) itr1.next();
				auth=element1[0]+"";
			}
			
			Log.debug("auth "+auth);
			
			if(Integer.parseInt(auth)==1)
			{				
				int res=0;
				
				String qr = "update bookmgmt.bm_user set sessionid = null where username = :username and sessionid = :sessionid";
				Query query = session.createSQLQuery(qr);
				query.setParameter("username",inputData.getUsername());
				query.setParameter("sessionid",inputData.getSessionid());
				res = query.executeUpdate();
				
				if(res==1)
				{
					ret="Success";
				}
				else
				{
					throw new Exception("failed to update session id");
				}
			}
			
		}
		catch (Exception e)
		{
			Log.debug("Error while logging off. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return ret;
		
	}
	
}