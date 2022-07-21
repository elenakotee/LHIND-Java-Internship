package com.springbootrest.controller;

import com.springbootrest.model.dto.BookingDTO;
import com.springbootrest.model.entity.Booking;
import com.springbootrest.model.entity.Employee;
import com.springbootrest.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private final BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}


	@GetMapping
	public List<BookingDTO> findAll() {
		return bookingService.retrieveAllBookings();
	}


	@GetMapping("/{bookingNumber}")
	public BookingDTO findByBookingNumber(@PathVariable("bookingNumber") String bookingNumber) {
		return bookingService.retrieveByBookingNumber(bookingNumber);

	}

	@GetMapping("/{employee}")
	public List<BookingDTO> findByEmployee(@PathVariable("employee") Employee employee) {
		return bookingService.retrieveByEmployee(employee);
	}

	@PostMapping
	public BookingDTO storeBooking(@RequestBody Booking booking) {
		return bookingService.storeBooking(booking);
	}

	@PutMapping
	public BookingDTO updateBooking(@RequestBody Booking booking) {
		return bookingService.storeBooking(booking);
	}

	@DeleteMapping
	public Boolean deleteBooking(@RequestParam(name = "id") Integer id) {
		return bookingService.deleteBooking(id);
	}
}
