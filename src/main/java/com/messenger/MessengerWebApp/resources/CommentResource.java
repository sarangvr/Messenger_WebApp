package com.messenger.MessengerWebApp.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {
	
	@GET
	public String test() {
		return "new sub resource";
	}
	
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId) {
		return "Method to return Comment Id: "+commentId + " For Messsage is: "+messageId;
		//By this we have access to entire URL
	}

}
