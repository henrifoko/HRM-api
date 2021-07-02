package com.frsummit.HRM.api.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.model.Attendance;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/attendances")
public class AttendanceController {

	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = "/attendance/remarkAttendances")
	public Iterable<Attendance> getRemarkAttendances() {
		Iterable<Attendance> attendances = null;
		try {
			attendances = this.HRMService.getRemarkAttendances();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return attendances;
	}
}
