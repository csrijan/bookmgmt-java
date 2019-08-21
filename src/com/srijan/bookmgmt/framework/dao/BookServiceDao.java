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
import com.srijan.bookmgmt.pojo.CreateBookInputPojo;
import com.srijan.bookmgmt.pojo.EditBookInputPojo;
import com.srijan.bookmgmt.pojo.ListAuthorsOutputPojo;
import com.srijan.bookmgmt.pojo.ListAuthorsOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListBookDetailsInputPojo;
import com.srijan.bookmgmt.pojo.ListBookDetailsOutputPojo;
import com.srijan.bookmgmt.pojo.ListBookDetailsOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListBooksOutputPojo;
import com.srijan.bookmgmt.pojo.ListBooksOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListCommonInputPojo;
import com.srijan.bookmgmt.pojo.ListGenresOutputPojo;
import com.srijan.bookmgmt.pojo.ListGenresOutputSetPojo;

public class BookServiceDao {
	
	public ListGenresOutputSetPojo listGenres(ListCommonInputPojo inputData) {
		
		ListGenresOutputSetPojo outpojo = new ListGenresOutputSetPojo();
		Set<ListGenresOutputPojo> liout = null;
		ListGenresOutputPojo lione = null;
		
		List<Object> li = null;
		List<Object> li1 = null;
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
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
				liout = new HashSet<ListGenresOutputPojo>();
				
				String qr = "select genre_id, genre_name from bookmgmt.bm_genre";
				Query query = session.createSQLQuery(qr);
				li = query.list();
				
				Object element[] = null;
				Iterator<Object> itr = li.iterator();			
				
				while(itr.hasNext())
				{				
					lione = new ListGenresOutputPojo();				
					element = (Object[]) itr.next();
					lione.setGenreid(Integer.parseInt(element[0]+""));
					lione.setGenre(element[1]+"");
					liout.add(lione);					
				}
				
				outpojo.setListGenresOutput(liout);
			}
			
		}
		catch (Exception e)
		{
			Log.debug("Error while listing Genres. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return outpojo;
		
	}
	
	public ListAuthorsOutputSetPojo listAuthors(ListCommonInputPojo inputData) {
		
		ListAuthorsOutputSetPojo outpojo = new ListAuthorsOutputSetPojo();
		Set<ListAuthorsOutputPojo> liout = null;
		ListAuthorsOutputPojo lione = null;
		
		List<Object> li = null;
		List<Object> li1 = null;
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
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
				liout = new HashSet<ListAuthorsOutputPojo>();
				
				String qr = "select author_id, author_name from bookmgmt.bm_author";
				Query query = session.createSQLQuery(qr);
				li = query.list();
				
				Object element[] = null;
				Iterator<Object> itr = li.iterator();			
				
				while(itr.hasNext())
				{				
					lione = new ListAuthorsOutputPojo();				
					element = (Object[]) itr.next();
					lione.setAuthorid(Integer.parseInt(element[0]+""));
					lione.setAuthorname(element[1]+"");
					liout.add(lione);					
				}
				
				outpojo.setListAuthorsOutput(liout);
			}
			
		}
		catch (Exception e)
		{
			Log.debug("Error while listing Authors. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return outpojo;
		
	}
	
	public String createBook(CreateBookInputPojo inputData) {
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
		List<Object> li1 = null;
		
		String ret="Success";
		
		String auth="";
		
		try
		{
			dbMngr = new BOOKMGMTDbManager();
			session = dbMngr.createSession();
			
			Log.debug("username "+inputData.getUsername());
			Log.debug("sessionid "+inputData.getSessionid());
			
			String qr0 = "select 1, 2 from bookmgmt.bm_user where username = :username and sessionid = :sessionid";
			Query query0 = session.createSQLQuery(qr0);
			query0.setParameter("username",inputData.getUsername());
			query0.setParameter("sessionid",inputData.getSessionid());
			li1 = query0.list();
			
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
			
				Log.debug("input book "+inputData.getBookname());
				Log.debug("input authors "+inputData.getAuthors());
				Log.debug("input genres "+inputData.getGenres());
				
				//for(String s : inputData.getAuthors())
				//{
				//	Log.debug("input author "+s);
				//}
				
				//Add new authors
				for(int i=0;i<inputData.getAuthors().size();i++)
				{
					//Log.debug("input author "+i+" "+inputData.getAuthors().get(i));
					boolean isnum=true;
					try
					{
						Integer.parseInt(inputData.getAuthors().get(i));
					}
					catch (NumberFormatException e)
					{
						isnum=false;
					}
					finally
					{
						if(!isnum)
						{						
							String qr = "insert into bookmgmt.bm_author (author_name) values (:authorname)";
							Query query = session.createSQLQuery(qr);
							query.setParameter("authorname",inputData.getAuthors().get(i));
							int res = query.executeUpdate();
							
							if(res==1)
							{
								String qr1 = "select author_id from bookmgmt.bm_author where author_name = :authorname";
								Query query1 = session.createSQLQuery(qr1);
								query1.setParameter("authorname",inputData.getAuthors().get(i));
								List<String> res1 = query1.list();
								String res2 = String.valueOf(res1.get(0));
								inputData.getAuthors().set(i, res2);
							}
							else
							{
								ret="Failed";
								throw new Exception("Critical Error!");
							}
						}
					}
				}
				Log.debug("after formatting");
				for(int i=0;i<inputData.getAuthors().size();i++)
				{
					Log.debug("input author "+i+" "+inputData.getAuthors().get(i));
				}
				
				//Add new genres
				for(int i=0;i<inputData.getGenres().size();i++)
				{
					Log.debug("input genre "+i+" "+inputData.getGenres().get(i));
					boolean isnum=true;
					try
					{
						Integer.parseInt(inputData.getGenres().get(i));
					}
					catch (NumberFormatException e)
					{
						isnum=false;
					}
					finally
					{
						if(!isnum)
						{						
							String qr = "insert into bookmgmt.bm_genre (genre_name) values (:genrename)";
							Query query = session.createSQLQuery(qr);
							query.setParameter("genrename",inputData.getGenres().get(i));
							int res = query.executeUpdate();
							
							if(res==1)
							{
								String qr1 = "select genre_id from bookmgmt.bm_genre where genre_name = :genrename";
								Query query1 = session.createSQLQuery(qr1);
								query1.setParameter("genrename",inputData.getGenres().get(i));
								List<String> res1 = query1.list();
								String res2 = String.valueOf(res1.get(0));
								inputData.getGenres().set(i, res2);
							}
							else
							{
								ret="Failed";
								throw new Exception("Critical Error!");
							}
						}
					}
				}
				Log.debug("after formatting");
				for(int i=0;i<inputData.getGenres().size();i++)
				{
					Log.debug("input genre "+i+" "+inputData.getGenres().get(i));
				}
				
				//Add the new book
				String qr = "insert into bookmgmt.bm_book (book_name) values (:bookname)";
				Query query = session.createSQLQuery(qr);
				query.setParameter("bookname",inputData.getBookname());
				int res = query.executeUpdate();
				
				if(res==1)
				{
					//Get Book ID
					String qr1 = "select book_id from bookmgmt.bm_book where book_name = :bookname";
					Query query1 = session.createSQLQuery(qr1);
					query1.setParameter("bookname",inputData.getBookname());
					List<String> res1 = query1.list();
					int bookId = Integer.valueOf(String.valueOf(res1.get(0)));
					
					Log.debug("READY!");
					
					//Update Book-Author
					for(int i=0;i<inputData.getAuthors().size();i++)
					{
						String qr2 = "insert into bookmgmt.bm_book_author values (:bookid,:authorid)";
						Query query2 = session.createSQLQuery(qr2);
						query2.setParameter("bookid",bookId);
						query2.setParameter("authorid",inputData.getAuthors().get(i));
						int res2 = query2.executeUpdate();
						
						if(res2!=1)
						{
							ret="Failed";
							throw new Exception("Critical Error!");
						}
					}
					
					//Update Book-Genre
					for(int i=0;i<inputData.getGenres().size();i++)
					{
						String qr2 = "insert into bookmgmt.bm_book_genre values (:bookid,:genreid)";
						Query query2 = session.createSQLQuery(qr2);
						query2.setParameter("bookid",bookId);
						query2.setParameter("genreid",inputData.getGenres().get(i));
						int res2 = query2.executeUpdate();
						
						if(res2!=1)
						{
							ret="Failed";
							throw new Exception("Critical Error!");
						}
					}
				}
				else
				{
					ret="Failed";
					throw new Exception("Critical Error!");
				}
			}
		}
		catch (Exception e)
		{
			ret="Failed";
			Log.debug("Error while creating book. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return ret;
	}
	
	public ListBooksOutputSetPojo listBooks(ListCommonInputPojo inputData) {
		
		ListBooksOutputSetPojo outpojo = new ListBooksOutputSetPojo();
		Set<ListBooksOutputPojo> liout = null;
		ListBooksOutputPojo lione = null;
		
		List<Object> li = null;
		List<Object> li1 = null;
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
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
				liout = new HashSet<ListBooksOutputPojo>();
				
				String qr = "select book_id, book_name from bookmgmt.bm_book";
				Query query = session.createSQLQuery(qr);
				li = query.list();
				
				Object element[] = null;
				Iterator<Object> itr = li.iterator();			
				
				while(itr.hasNext())
				{				
					lione = new ListBooksOutputPojo();				
					element = (Object[]) itr.next();
					lione.setBookid(Integer.parseInt(element[0]+""));
					lione.setBookname(element[1]+"");
					liout.add(lione);					
				}
				
				outpojo.setListBooksOutput(liout);
			}
		}
		catch (Exception e)
		{
			Log.debug("Error while listing books. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return outpojo;
		
	}
	
	public ListBookDetailsOutputSetPojo listBookDetails(ListBookDetailsInputPojo inputData) {
		
		ListBookDetailsOutputSetPojo outpojo = new ListBookDetailsOutputSetPojo();
		Set<ListBookDetailsOutputPojo> liout = null;
		ListBookDetailsOutputPojo lione = null;
		
		List<Object> li = null;
		List<Object> li1 = null;
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
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
				liout = new HashSet<ListBookDetailsOutputPojo>();
				
				String qr = "select b.book_id, a.author_id, g.genre_id from bm_book b "+
				            "left join bm_book_author ba on ba.book_id = b.book_id "+
						    "left join bm_book_genre bg on bg.book_id = b.book_id "+
				            "left join bm_author a on a.author_id = ba.author_id "+
						    "left join bm_genre g on g.genre_id = bg.genre_id "+
				            "where b.book_id = :bookid";
				
				Query query = session.createSQLQuery(qr);
				query.setParameter("bookid",inputData.getBookid());
				li = query.list();
				
				Object element[] = null;
				Iterator<Object> itr = li.iterator();			
				
				while(itr.hasNext())
				{				
					lione = new ListBookDetailsOutputPojo();				
					element = (Object[]) itr.next();
					lione.setBookid(Integer.parseInt(element[0]+""));
					lione.setAuthorid(Integer.parseInt(element[1]+""));
					lione.setGenreid(Integer.parseInt(element[2]+""));
					liout.add(lione);					
				}
				
				outpojo.setListBookDetailsOutput(liout);
			}
		}
		catch (Exception e)
		{
			Log.debug("Error while getting single book details. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return outpojo;
			
	}
	
	public String deleteBook(ListBookDetailsInputPojo inputData) {

		List<Object> li1 = null;
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
		String ret="Success";
		
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
				
				String qr2 = "delete from bookmgmt.bm_book where book_id = :bookid";				
				Query query2 = session.createSQLQuery(qr2);
				query2.setParameter("bookid",inputData.getBookid());
				int res = query2.executeUpdate();
				
				if(res==1)
				{
					String qr3 = "delete from bookmgmt.bm_book_author where book_id = :bookid";
					Query query3 = session.createSQLQuery(qr3);
					query3.setParameter("bookid",inputData.getBookid());
					res = query3.executeUpdate();
					
					if(res<1)
					{
						ret="Failed";
						throw new Exception("Critical Error!");
					}
					
					String qr4 = "delete from bookmgmt.bm_book_genre where book_id = :bookid";
					Query query4 = session.createSQLQuery(qr4);
					query4.setParameter("bookid",inputData.getBookid());
					res = query4.executeUpdate();
					
					if(res<1)
					{
						ret="Failed";
						throw new Exception("Critical Error!");
					}
				}
				else
				{
					ret="Failed";
					throw new Exception("Critical Error!");
				}
				
			}
		}
		catch (Exception e)
		{
			Log.debug("Error while deleting book. (Error in HQL Query) "
					+ e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			dbMngr.close();
		}
		
		return ret;
			
	}
	
	public String editBook(EditBookInputPojo inputData) {
		
		BOOKMGMTDbManager dbMngr = null;
		Session session = null;
		
		List<Object> li1 = null;
		
		String ret="Success";
		
		String auth="";
		
		try
		{
			dbMngr = new BOOKMGMTDbManager();
			session = dbMngr.createSession();
			
			Log.debug("username "+inputData.getUsername());
			Log.debug("sessionid "+inputData.getSessionid());
			
			String qr0 = "select 1, 2 from bookmgmt.bm_user where username = :username and sessionid = :sessionid";
			Query query0 = session.createSQLQuery(qr0);
			query0.setParameter("username",inputData.getUsername());
			query0.setParameter("sessionid",inputData.getSessionid());
			li1 = query0.list();
			
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
			
				Log.debug("input book id "+inputData.getBookid());
				Log.debug("input authors "+inputData.getAuthors());
				Log.debug("input genres "+inputData.getGenres());
				
				//Add new authors
				for(int i=0;i<inputData.getAuthors().size();i++)
				{
					//Log.debug("input author "+i+" "+inputData.getAuthors().get(i));
					boolean isnum=true;
					try
					{
						Integer.parseInt(inputData.getAuthors().get(i));
					}
					catch (NumberFormatException e)
					{
						isnum=false;
					}
					finally
					{
						if(!isnum)
						{						
							String qr5 = "insert into bookmgmt.bm_author (author_name) values (:authorname)";
							Query query5 = session.createSQLQuery(qr5);
							query5.setParameter("authorname",inputData.getAuthors().get(i));
							int res = query5.executeUpdate();
							
							if(res==1)
							{
								String qr6 = "select author_id from bookmgmt.bm_author where author_name = :authorname";
								Query query6 = session.createSQLQuery(qr6);
								query6.setParameter("authorname",inputData.getAuthors().get(i));
								List<String> res1 = query6.list();
								String res2 = String.valueOf(res1.get(0));
								inputData.getAuthors().set(i, res2);
							}
							else
							{
								ret="Failed";
								throw new Exception("Critical Error!");
							}
						}
					}
				}
				Log.debug("after formatting");
				for(int i=0;i<inputData.getAuthors().size();i++)
				{
					Log.debug("input author "+i+" "+inputData.getAuthors().get(i));
				}
				
				//Add new genres
				for(int i=0;i<inputData.getGenres().size();i++)
				{
					Log.debug("input genre "+i+" "+inputData.getGenres().get(i));
					boolean isnum=true;
					try
					{
						Integer.parseInt(inputData.getGenres().get(i));
					}
					catch (NumberFormatException e)
					{
						isnum=false;
					}
					finally
					{
						if(!isnum)
						{						
							String qr7 = "insert into bookmgmt.bm_genre (genre_name) values (:genrename)";
							Query query7 = session.createSQLQuery(qr7);
							query7.setParameter("genrename",inputData.getGenres().get(i));
							int res = query7.executeUpdate();
							
							if(res==1)
							{
								String qr8 = "select genre_id from bookmgmt.bm_genre where genre_name = :genrename";
								Query query8 = session.createSQLQuery(qr8);
								query8.setParameter("genrename",inputData.getGenres().get(i));
								List<String> res1 = query8.list();
								String res2 = String.valueOf(res1.get(0));
								inputData.getGenres().set(i, res2);
							}
							else
							{
								ret="Failed";
								throw new Exception("Critical Error!");
							}
						}
					}
				}
				Log.debug("after formatting");
				for(int i=0;i<inputData.getGenres().size();i++)
				{
					Log.debug("input genre "+i+" "+inputData.getGenres().get(i));
				}
				
				String qr1 = "delete from bookmgmt.bm_book_author where book_id = :bookid";
				Query query1 = session.createSQLQuery(qr1);
				query1.setParameter("bookid",inputData.getBookid());
				int res = query1.executeUpdate();
				
				if(res<1)
				{
					ret="Failed";
					throw new Exception("Critical Error!");
				}
				else
				{
					for(int i=0;i<inputData.getAuthors().size();i++)
					{
						String qr2 = "insert into bookmgmt.bm_book_author values (:bookid,:authorid)";
						Query query2 = session.createSQLQuery(qr2);
						query2.setParameter("bookid",inputData.getBookid());
						query2.setParameter("authorid",inputData.getAuthors().get(i));
						res = query2.executeUpdate();
						
						if(res!=1)
						{
							ret="Failed";
							throw new Exception("Critical Error!");
						}
					}
				}
				
				String qr3 = "delete from bookmgmt.bm_book_genre where book_id = :bookid";
				Query query3 = session.createSQLQuery(qr3);
				query3.setParameter("bookid",inputData.getBookid());
				res = query3.executeUpdate();
				
				if(res<1)
				{
					ret="Failed";
					throw new Exception("Critical Error!");
				}
				else
				{
					for(int i=0;i<inputData.getGenres().size();i++)
					{
						String qr4 = "insert into bookmgmt.bm_book_genre values (:bookid,:genreid)";
						Query query4 = session.createSQLQuery(qr4);
						query4.setParameter("bookid",inputData.getBookid());
						query4.setParameter("genreid",inputData.getGenres().get(i));
						res = query4.executeUpdate();
						
						if(res!=1)
						{
							ret="Failed";
							throw new Exception("Critical Error!");
						}
					}
				}
				
			}
		}
		catch (Exception e)
		{
			Log.debug("Error while editing book. (Error in HQL Query) "
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