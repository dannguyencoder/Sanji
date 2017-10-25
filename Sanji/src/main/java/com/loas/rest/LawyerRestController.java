package com.loas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loas.model.Lawyer;
import com.loas.service.LawyerService;

@RestController
public class LawyerRestController {
	@Autowired
	LawyerService _lawyerService;

	@RequestMapping(path = "/lawyerList", method = RequestMethod.GET)
	public List<Lawyer> getAllEmployees() {
		return _lawyerService.getAllLawyers();
	}

}
