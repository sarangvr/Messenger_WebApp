package com.messenger.MessengerWebApp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private long id;
    private String message;
    private Date created;
    private String author;
//    private Map<Long, Comment> comments=new HashMap<>();
    private List<Link> links=new ArrayList<>();
    
    
    public List<Link> getLinks() {
		return links;
	}



	public void setLinks(List<Link> links) {
		this.links = links;
	}



	//Model classes have a no-argument constructor(while dealing with xml or json convertion)
    public Message() {
    	
    }
    
    public void addLink(String url, String rel) {
		Link link=new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
    
    

    public Message(long id, String message, String author) {
    	this.id = id;
    	this.message = message;
    	this.author = author;
    	this.created = new Date();
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	

}
