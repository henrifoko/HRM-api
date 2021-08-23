package com.frsummit.HRM.api.rest.controller;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rest.postprocessing.PaginationPostprocessing;
import com.frsummit.HRM.api.rest.postprocessing.SortPostprocessing;
import com.frsummit.HRM.api.server.entity.User;
import com.frsummit.HRM.api.server.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = { "/", "" })
	public Iterable<User> getAllUsers(@RequestParam(required = false) boolean sort,
			@RequestParam(required = false, name = "sort_attribute") String sortAttribute,
			@RequestParam(required = false, name = "sort_order") String sortOrder,
			@RequestParam(required = false, name = "limit") Integer limit,
			@RequestParam(required = false, name = "page") Integer page) throws Exception {
		if (limit == null) {
			limit = 0;
		}
		if (page == null) {
			page = 0;
		}
		Iterable<User> users = null;
		
		try {
			users = this.HRMService.getAllUsers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		SortPostprocessing<User> sorter = new SortPostprocessing<User>(sort, sortAttribute, sortOrder);
		PaginationPostprocessing<User> paginator = new PaginationPostprocessing<User>(limit, page);
		return paginator.process(sorter.process((List<User>) users));
	}

	@GetMapping(value = "/id/{userId}") // /api/{collection}/{document}/{data}
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

	@PostMapping(value = { "/", "" })
	public User createUser(@RequestBody User user) {
		// TODO: AJOUTER DES CONTRÔLES DE VALIDATION DES ENTREES
		try {
			return this.HRMService.createUser(user);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PutMapping(value = "/id/{userId}")
	public User updateUser(@PathVariable String userId, @RequestBody User user) {
		// TODO AJOUTER DES CONTRÔLES DE VALIDATION DES ENTREES
		try {
			return this.HRMService.updateUser(userId, user.getFirstName(), user.getMiddleName(), user.getLastName(),
					user.getEmail(), user.getDepartment(), user.getDesignation(), user.getDob(), user.getSex(),
					user.getPhone(), user.getBloodGroup(), user.getFatherName(), user.getMotherName(), user.getNid(),
					user.getPassportNumber(), user.getPresentAddress(), user.getPermanentAddress(),
					user.getNationality(), user.getMyRole(), user.getImageName(), user.getIncomeTexNo(),
					user.getActive());
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PatchMapping(value = "/id/{userId}")
	public User updateUserProperty(@PathVariable String userId, @RequestParam Map<String, String> params) {
		try {
			User user = null;
			for (String prop : params.keySet()) {
				user = this.HRMService.updateUserProperty(userId, prop, params.get(prop));
			}
			return user;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@DeleteMapping(value = "/id/{userId}")
	public User deleteUser(@PathVariable String userId) {
		try {
			User user = this.HRMService.deleteUser(userId);
			return user;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

}
