package com.springbootrest.service;

import com.springbootrest.model.dto.BookingDTO;
import com.springbootrest.model.entity.Booking;
import com.springbootrest.model.entity.Employee;

import java.util.List;

public interface BookingService {

	List<BookingDTO> retrieveAllBookings();

	BookingDTO retrieveByBookingNumber(String bookingNumber);

	List<BookingDTO> retrieveByEmployee(Employee employee);

	BookingDTO storeBooking(Booking booking);

	Boolean deleteBooking(Integer id);

	Boolean clearDatabase();

}


