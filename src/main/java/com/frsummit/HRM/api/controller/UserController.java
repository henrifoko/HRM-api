package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.model.User;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = {"/", ""})
	public Iterable<User> getAllUsers() {
		Iterable<User> users = null;
		try {
			users = this.HRMService.getAllUsers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return users;
	}

	@GetMapping(value = "/id/{userId}")
	public User getUserById(@PathVariable("userId") String userId) {
		try {
			return this.HRMService.getUserById(userId);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/dept/{dept}")
	public Iterable<User> getUserByDepatment(@PathVariable("dept") String dept) {
		try {
			return this.HRMService.getUserByDepartment(dept);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/email/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		try {
			return this.HRMService.getUserByEmail(email);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping(value = {"/", ""})
	public User createUser(@RequestBody User user) {
		/**
		 * @TODO: AJOUTER DES CONTRÔLES DE VALIDATION DES ENTREES
		 */
		try {
			return this.HRMService.createUser(user);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(value = "/id/{userId}")
	public User updateUser(@PathVariable String userId, @RequestBody User user) {
		/**
		 * @TODO: AJOUTER DES CONTRÔLES DE VALIDATION DES ENTREES
		 */
		try {
			return this.HRMService.updateUser(userId, user);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

}
