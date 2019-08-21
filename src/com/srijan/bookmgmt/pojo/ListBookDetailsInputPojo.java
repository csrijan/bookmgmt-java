package com.srijan.bookmgmt.pojo;

public class ListBookDetailsInputPojo {
	
	String username = null;
	String sessionid = null;
	int bookid = 0;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

}