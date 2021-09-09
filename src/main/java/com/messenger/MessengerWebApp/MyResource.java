package com.messenger.MessengerWebApp;
//http://localhost:8082/MessengerWebApp/webapi/messages
import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("myresource")
public class MyResource {

    //My Resource
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
}
