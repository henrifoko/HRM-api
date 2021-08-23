package com.frsummit.HRM.api.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/test")
public class TestController {
	
	@RequestMapping(value = "/foo", method = RequestMethod.GET)
	public String test() {
		return "Test foo";
	}
}
