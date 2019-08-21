package com.srijan.bookmgmt.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.srijan.bookmgmt.framework.dao.AuthServiceDao;
import com.srijan.bookmgmt.framework.dao.BookServiceDao;
import com.srijan.bookmgmt.pojo.AuthUserInputPojo;
import com.srijan.bookmgmt.pojo.AuthUserOutputSetPojo;
import com.srijan.bookmgmt.pojo.CreateBookInputPojo;
import com.srijan.bookmgmt.pojo.EditBookInputPojo;
import com.srijan.bookmgmt.pojo.ListAuthorsOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListBookDetailsInputPojo;
import com.srijan.bookmgmt.pojo.ListBookDetailsOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListBooksOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListCommonInputPojo;
import com.srijan.bookmgmt.pojo.ListGenresOutputSetPojo;

@Path("/bookService")
public class BookService {
	
	@POST
	@Path("/listGenres")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ListGenresOutputSetPojo listGenres(ListCommonInputPojo inputData) {
		
		BookServiceDao dao = new BookServiceDao();
		ListGenresOutputSetPojo outpojo = dao.listGenres(inputData);
		
		return outpojo;
		
	}
	
	@POST
	@Path("/listAuthors")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ListAuthorsOutputSetPojo listAuthors(ListCommonInputPojo inputData) {
		
		BookServiceDao dao = new BookServiceDao();
		ListAuthorsOutputSetPojo outpojo = dao.listAuthors(inputData);
		
		return outpojo;
		
	}
	
	@POST
	@Path("/createBook")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public String createBook(CreateBookInputPojo inputData) {
		
		BookServiceDao dao = new BookServiceDao();
		String ret = dao.createBook(inputData);
		
		return ret;
		
	}
	
	@POST
	@Path("/listBooks")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ListBooksOutputSetPojo listBooks(ListCommonInputPojo inputData) {
		
		BookServiceDao dao = new BookServiceDao();
		ListBooksOutputSetPojo ret = dao.listBooks(inputData);
		
		return ret;
		
	}
	
	@POST
	@Path("/listBookDetails")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ListBookDetailsOutputSetPojo listBookDetails(ListBookDetailsInputPojo inputData) {
		
		BookServiceDao dao = new BookServiceDao();
		ListBookDetailsOutputSetPojo ret = dao.listBookDetails(inputData);
		
		return ret;
		
	}
	
	@POST
	@Path("/deleteBook")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public String deleteBook(ListBookDetailsInputPojo inputData) {
		
		BookServiceDao dao = new BookServiceDao();
		String ret = dao.deleteBook(inputData);
		
		return ret;
		
	}
	
	@POST
	@Path("/editBook")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public String editBook(EditBookInputPojo inputData) {
		
		BookServiceDao dao = new BookServiceDao();
		String ret = dao.editBook(inputData);
		
		return ret;
		
	}
	
}