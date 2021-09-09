package com.messenger.MessengerWebApp.resources;

import java.util.List; 

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.messenger.MessengerWebApp.model.Message;
import com.messenger.MessengerWebApp.resources.beans.MessageFilterBean;
import com.messenger.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})//Like this we can support multiple content types in JAX-RS >>>content negotiation
public class MessageResource {
MessageService messageService = new MessageService();
	
//	@GET
//	public List<Message> getMessages(@QueryParam("year") int year,// In queryparam with ?k=value format
//									 @QueryParam("start") int start,		
//									 @QueryParam("size") int size) {			
		@GET
		public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {			
			
		if(filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(),filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId")long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId")long id) {
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")//Implementing HATEOAS separate URL and rel
	public Message getMessage(@PathParam("messageId")long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
//		String uri=uriInfo.getBaseUriBuilder()
//		.path(MessageResource.class)
//		.path(Long.toString(message.getId()))
//		.build()
//		.toString();
		
		//Right Click, Refracter, extract method<<
		String uri = getUriForSelf(uriInfo, message);
		
		message.addLink(uri, "self");
		return message;	//http://localhost:8082/MessengerWebApp/webapi/messages/1
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();
		return uri;
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}
