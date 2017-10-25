package com.loas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loas.model.Booking;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Integer>, PagingAndSortingRepository<Booking, Integer> {

	List<Booking> findByLastName(String q);

	Booking findByBookingId(int id);
	
	@Query("select b from Booking b where b.status = 'Pending'")
	List<Booking> getPendingBookings();
	
}
