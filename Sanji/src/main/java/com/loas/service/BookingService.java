package com.loas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.loas.model.Booking;

public interface BookingService {

	void addBooking(Booking booking);

	Iterable<Booking> findAllBookings();

	List<Booking> getAllBookings();

	List<Booking> search(String q);

	Booking findBookingById(int id);

	Booking findOne(int id);

	void delete(int id);

	void save(Booking booking);

	Page<Booking> findAllPageable(Pageable pageable);
	
	List<Booking> getPendingBookings();

}
