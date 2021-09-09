package com.messenger.messenger.service;
//Comments classes part not included
//Using Web application Exception not included
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.messenger.MessengerWebApp.database.DatabaseClass;
import com.messenger.MessengerWebApp.exception.DataNotFoundException;
import com.messenger.MessengerWebApp.model.Message;
import com.mongodb.client.model.ReturnDocument;


public class MessageService {
	
	private  Map<Long, Message> messages=DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hi John !","John")) ;
		messages.put(2L, new Message(2, "Hi Pitbull !", "PitBull"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	//Pagination and Filtering 
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear=new ArrayList<>();
		Calendar cal=Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start,int size){
		ArrayList<Message> list=new ArrayList<Message>(messages.values());
		if(start+size > list.size()) return new ArrayList<Message>();
		
		return list.subList(start, start+size);
	}
	
	public Message getMessage(long id) {
		Message message= messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with ID "+ id +" not found");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	public Message updateMessage(Message message) {
		if(message.getId() <=0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	public Message removeMessage(long id) {
		return messages.remove(id);
	}

}
