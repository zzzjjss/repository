package com.uf.rest.exception;

public class UserExistException extends Exception{
	private static final long serialVersionUID = 1L;
	public UserExistException(String msg){
		super(msg);
	}
}
