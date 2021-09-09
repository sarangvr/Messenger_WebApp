package com.messenger.MessengerWebApp.resources;

import java.net.http.HttpHeaders; 

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.*;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("authSessionID") String header,
											//We are able to sent some tokens used more
											@CookieParam("name") String cookie) {
		return "MatrixParam :"+matrixParam + "Header Paran: "+header + "COOKIE: "+cookie;
		
	}
	
	@GET
	@Path("context")//We get the uri or path using this method
	public String getPAramUsingContext(@Context UriInfo uriinfo,@Context javax.ws.rs.core.HttpHeaders headers) {
		String path=uriinfo.getAbsolutePath().toString();
		String cookies=headers.getCookies().toString();
		return "Path: "+path + "Cookies: "+ cookies;
	}
			

}
