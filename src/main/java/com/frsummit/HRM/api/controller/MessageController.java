package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.model.Message;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {
	
	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = "/message/allMessages")
	public Iterable<Message> getAllMessages() {
		Iterable<Message> messages = null;
		try {
			messages = this.HRMService.getAllMessages();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return messages;
	}

}
