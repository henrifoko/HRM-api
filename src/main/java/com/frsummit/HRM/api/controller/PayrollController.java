package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.model.Payroll;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/payrolls")
public class PayrollController {
	
	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = "/payroll/allPayrolls")
	public Iterable<Payroll> getAllPayrolls() {
		Iterable<Payroll> payrolls = null;
		try {
			payrolls = this.HRMService.getAllPayrolls();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return payrolls;
	}

}
