package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.model.Leaves;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/leaves")
public class LeaveController {

	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = "/leave/allLeaves")
	public Iterable<Leaves> getAllLeaves() {
		Iterable<Leaves> leaves = null;
		try {
			leaves = this.HRMService.getAllLeaves();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return leaves;
	}
	
}
