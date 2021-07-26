package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.entity.Links;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/links")
public class LinkController {
	
	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = "/link/allLinks")
	public Iterable<Links> getAllLinks() {
		Iterable<Links> links = null;
		try {
			links = this.HRMService.getAllLinks();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return links;
	}
	
}
