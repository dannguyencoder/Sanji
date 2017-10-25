package com.loas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.loas.model.Booking;
import com.loas.repository.BookingRepository;
import com.loas.service.BookingService;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public void addBooking(Booking booking) {
		bookingRepository.save(booking);
	}

	@Override
	public Iterable<Booking> findAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> search(String q) {
		return bookingRepository.findByLastName(q);
	}

	@Override
	public Booking findBookingById(int id) {
		return bookingRepository.findByBookingId(id);
	}

	@Override
	public Booking findOne(int id) {
		return bookingRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		bookingRepository.delete(id);
	}

	@Transactional
	@Override
	public Page<Booking> findAllPageable(Pageable pageable) {
		return bookingRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public void save(Booking booking) {
		bookingRepository.saveAndFlush(booking);
	}
	
	@Override
	public List<Booking> getPendingBookings() {
		return bookingRepository.getPendingBookings();
	}

}
