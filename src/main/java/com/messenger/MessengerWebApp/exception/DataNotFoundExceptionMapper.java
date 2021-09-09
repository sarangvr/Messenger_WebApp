package com.messenger.MessengerWebApp.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.messenger.MessengerWebApp.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage=new ErrorMessage(exception.getMessage(), 404, "https://www.javatpoint.com/exception-handling-in-java");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}//http://localhost:8082/MessengerWebApp/webapi/messages/200

}
