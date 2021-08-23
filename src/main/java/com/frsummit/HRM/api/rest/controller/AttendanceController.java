package com.frsummit.HRM.api.rest.controller;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.server.entity.Attendance;
import com.frsummit.HRM.api.server.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/attendances")
public class AttendanceController {

	@Autowired
	HRMRemoteService HRMService;

	@GetMapping(value = { "/", "" })
	public Iterable<Attendance> getAllAttendances() {
		Iterable<Attendance> attendances = null;
		try {
			attendances = this.HRMService.getAllAttendances();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return attendances;
	}

	@GetMapping(value = { "/user/{userId}" })
	public Iterable<Attendance> getAllSignedAttendances(@PathVariable String userId) {
		Iterable<Attendance> attendances = null;
		try {
			attendances = this.HRMService.getAllSignedAttendance(userId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return attendances;
	}

	@GetMapping(value = { "/remark" })
	public Iterable<Attendance> getAllRemarkAttendances(String userId) {
		Iterable<Attendance> attendances = null;
		try {
			attendances = this.HRMService.getAllRemarkAttendance();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return attendances;
	}

	@PostMapping(value = { "", "/" })
	public Attendance createAttendance(@RequestBody Attendance attendance) {
		try {
			return this.HRMService.createAttendance(attendance);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@PatchMapping(value = { "/attendance/{attendId}" })
	public Attendance setAttendanceRemark(@PathVariable String attendId, @RequestParam String remark) {
		try {
			if (remark.equals("REMARK")) {
				return this.HRMService.setAttendanceRemark(attendId);
			} else if (remark.equals("FALSE")) {
				return this.HRMService.removeAttendanceFromRemarkList(attendId);
			} else { // TODO Raise an exception: Value Exception
				return this.HRMService.getAttendanceById(Integer.parseInt(attendId));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = { "/attendance/{attendId}" })
	public Attendance getAttendance(@PathVariable int attendId) {
		try {
			return this.HRMService.getAttendanceById(attendId);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
}
