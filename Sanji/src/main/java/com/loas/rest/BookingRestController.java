package com.loas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loas.service.BookingService;
import com.loas.model.Booking;

@RestController
public class BookingRestController {
	
	@Autowired
	BookingService _bookingService;

	@RequestMapping(path = "/bookingList", method = RequestMethod.GET)
	public List<Booking> getAllEmployees() {
		return _bookingService.getAllBookings();
	}
}
