package com.loas.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.loas.model.Booking;

public class CSVHelper {

	public static List<Booking> read(String fileName) {
		try {
			List<Booking> bookings = new ArrayList<>();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				Booking booking = new Booking();
				String[] strBooking = line.trim().split(",");
				booking.setFirstName(strBooking[0].trim());
				booking.setLastName(strBooking[1].trim());
				booking.setDob(strBooking[2].trim());
				booking.setGender(strBooking[3].trim());
				booking.setEmail(strBooking[4].trim());
				booking.setPhone(strBooking[5].trim());
				booking.setAddress(strBooking[6].trim());
				booking.setPrefer_date(strBooking[7].trim());
				booking.setPrefer_time(strBooking[8].trim());
				booking.setCase_cat_id(Integer.parseInt(strBooking[9].trim()));
				booking.setStatus(strBooking[10].trim());
				booking.setNote(strBooking[11].trim());
				bookings.add(booking);
			}
			bufferedReader.close();
			return bookings;
		} catch (Exception e) {
			return null;
		}
	}

}
