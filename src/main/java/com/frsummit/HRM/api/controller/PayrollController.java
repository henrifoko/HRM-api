package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.frsummit.HRM.api.rmi.entity.Payroll;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/payrolls")
public class PayrollController {
	
	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = {"", "/"})
	public Iterable<Payroll> getAllPayrolls() {
		Iterable<Payroll> payrolls = null;
		try {
			payrolls = this.HRMService.getAllPayrolls();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return payrolls;
	}
	
	@GetMapping(value = "/user/{userId}")
	public Iterable<Payroll> getPayrollForSpecificUser(@PathVariable String userId, @RequestParam boolean current) {
		Iterable<Payroll> payrolls = null;
		try {
			if (current) {
				payrolls = this.HRMService.getCurrentPayrollForUser(userId);
			} else {
				payrolls = this.HRMService.getPayrollForSpecificUser(userId);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return payrolls;
	}
	
	@PostMapping(value = {"/", ""})
	public Payroll createPayroll(@RequestBody Payroll payroll) {
		try {
			return this.HRMService.createPayroll(payroll);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

}
