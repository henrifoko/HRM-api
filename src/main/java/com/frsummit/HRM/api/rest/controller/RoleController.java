package com.frsummit.HRM.api.rest.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.server.entity.Role;
import com.frsummit.HRM.api.server.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {

	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = "/role/allRoles")
	public Iterable<Role> getAllRoles() {
		Iterable<Role> roles = null;
		try {
			roles = this.HRMService.getAllRoles();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return roles;
	}

}
