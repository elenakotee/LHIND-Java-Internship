package com.springbootrest.service.impl;


import com.springbootrest.converter.AbstractConverter;
import com.springbootrest.converter.BookingConverter;
import com.springbootrest.model.dto.BookingDTO;
import com.springbootrest.model.entity.Booking;
import com.springbootrest.model.entity.Employee;
import com.springbootrest.repository.BookingRepository;
import com.springbootrest.service.BookingService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final AbstractConverter<Booking, BookingDTO> bookingConverter;

	public BookingServiceImpl(final BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
		bookingConverter = new BookingConverter();
	}


	@Override
	public List<BookingDTO> retrieveAllBookings() {
		return bookingRepository.findAll()
				.stream()
				.map(bookingConverter::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public BookingDTO retrieveByBookingNumber(String bookingNumber) {
		return bookingConverter.toDTO(bookingRepository.findByBookingNumber(bookingNumber));
	}

	@Override
	public List<BookingDTO> retrieveByEmployee(Employee employee) {
		return bookingRepository.findByEmployee(employee)
				.stream()
				.map(bookingConverter::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public BookingDTO storeBooking(Booking booking) {
		return bookingConverter.toDTO(bookingRepository.save(booking));
	}

	@Override
	public Boolean deleteBooking(Integer id) {
		bookingRepository.deleteById(id);
		return true;
	}

	@Override
	public Boolean clearDatabase() {
		bookingRepository.deleteAll();
		return true;
	}
}
