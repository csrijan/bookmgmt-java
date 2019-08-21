package com.srijan.bookmgmt.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.srijan.bookmgmt.framework.dao.AuthServiceDao;
import com.srijan.bookmgmt.pojo.AuthUserInputPojo;
import com.srijan.bookmgmt.pojo.AuthUserOutputSetPojo;
import com.srijan.bookmgmt.pojo.ListCommonInputPojo;

@Path("/authService")
public class AuthService {
	
	@POST
	@Path("/authUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public AuthUserOutputSetPojo authUser(AuthUserInputPojo inputData) {
		
		AuthServiceDao dao = new AuthServiceDao();
		AuthUserOutputSetPojo outpojo = dao.authUser(inputData);
		
		return outpojo;
		
	}
	
	@POST
	@Path("/addUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public String addUser(AuthUserInputPojo inputData) {
		
		AuthServiceDao dao = new AuthServiceDao();
		String ret = dao.addUser(inputData);
		
		return ret;
		
	}
	
	@POST
	@Path("/logoffUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public String logoffUser(ListCommonInputPojo inputData) {
		
		AuthServiceDao dao = new AuthServiceDao();
		String ret = dao.logoffUser(inputData);
		
		return ret;
		
	}
	
}