package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.model.HRRecord;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/HRRecords")
public class HRRecordController {
	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = "/HRRecord/allHRRecords")
	public Iterable<HRRecord> getAllHRRecords() {
		Iterable<HRRecord> allHRRecords = null;
		try {
			allHRRecords = this.HRMService.getAllHRRecords();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return allHRRecords;
	}
}
