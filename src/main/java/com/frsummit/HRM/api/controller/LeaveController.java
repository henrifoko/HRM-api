package com.frsummit.HRM.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.rmi.entity.HRRecord;
import com.frsummit.HRM.api.rmi.entity.Leaves;
import com.frsummit.HRM.api.rmi.entity.User;
import com.frsummit.HRM.api.service.FileService;
import com.frsummit.HRM.api.service.HRMRemoteService;

@RestController
@RequestMapping(value = "/api/leaves")
public class LeaveController {

	@Autowired
	HRMRemoteService HRMService;
	
	@Autowired
	FileService fileService;

	@GetMapping(value = { "", "/" })
	public Iterable<Leaves> getAllLeaves() {
		Iterable<Leaves> leaves = null;
		try {
			leaves = this.HRMService.getAllLeaves();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return leaves;
	}

	@GetMapping(value = "/user/{userId}")
	public Iterable<Leaves> getByUserId(@PathVariable String userId, @RequestParam boolean granted) {
		Iterable<Leaves> leaves = null;
		try {
			if (granted) {
				leaves = this.HRMService.getGrantedLeavesForUser(userId);
			} else {
				leaves = this.HRMService.getAllLeavesForUser(userId);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return leaves;
	}

	@GetMapping(value = "/role/{role}")
	public Iterable<Leaves> getByUserId(@PathVariable String role) {
		Iterable<Leaves> leaves = null;
		try {
			leaves = this.HRMService.getLeavesByRole(role);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return leaves;
	}

	@GetMapping(value = "/leave/{leaveId}")
	public Leaves getByLeaveId(@PathVariable int leaveId) {
		try {
			return this.HRMService.getLeaveById(leaveId);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ========================================================================
	// === === === === === Exemple de endpoint RPC
	// ========================================================================

	@RequestMapping(value = "/leave.cancel")
	public Leaves cancelLeave(@RequestParam(name = "id") int leaveId) {
		try {
			return this.HRMService.cancelLeave(leaveId);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// TODO Test this controller
	@PostMapping(value = { "/", "" })
	public Leaves createLeave(@RequestBody Leaves leaves) {
		try {	
			return this.HRMService.createLeaves(leaves);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	// TODO Test this controller
	@PatchMapping(value = { "/leave/{leaveId}" })
	public Leaves updateLeaveStatus(@PathVariable int leaveId, @RequestParam Optional<String> status,
			@RequestParam Optional<String> leaveActionBy, @RequestParam Optional<String> modifyTo) {
		try {
			if (leaveActionBy.isPresent() || modifyTo.isPresent()) {
				return this.HRMService.updateLeave(leaveId, status.get(), leaveActionBy.get(), modifyTo.get());
			} else {
				return this.HRMService.updateLeaveStatus(leaveId, status.get());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/report_consolidated/")
	public List<HRRecord> getLeaveReportConsolidated() {
		try {
			return this.HRMService.getLeaveReportConsolidated();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/report-consolidated/dept/{dept}")
	public List<HRRecord> getLeaveReportConsolidated(@PathVariable String dept,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
		try {
			return this.HRMService.getLeaveReportConsolidatedSelectedByDate(dept, fromDate.toString(),
					toDate.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping(value = "/leave.print_individual_report")
	public ResponseEntity<Object> printIndividualReport(@RequestBody User user) {
		byte[] byteFile = null;
		
		// 1. Get the byte array from the server
		try {
			byteFile = this.HRMService.printIndividualReport(user);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

		// 2. Save the byte array in a file into the RMI client
		String fileName = fileService.saveTempFile(byteFile);
		
		// 3. Return a stream to the file on the RMI client
		File file = new File(fileName);
		InputStreamResource resource;
		
		try {
			System.out.println("Transfering the temp file [" + fileName + "] ...");
			resource = new InputStreamResource(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "individual_report.pdf"));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/pdf")).body(resource);

		return responseEntity;
	}
}
