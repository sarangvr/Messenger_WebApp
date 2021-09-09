package com.messenger.MessengerWebApp.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.messenger.MessengerWebApp.model.ErrorMessage;
//Will get Exception in JSON format
//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>  {
	
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(), 500, "https://www.javatpoint.com/exception-handling-in-java");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
