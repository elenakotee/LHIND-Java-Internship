package com.springbootrest.model.builder;

import com.springbootrest.model.entity.Booking;
import com.springbootrest.model.enums.BookingStatus;

import java.util.Date;

public class BookingBuilder {

	Booking booking = new Booking();

	public Booking build() {
		return booking;
	}

	public BookingBuilder withId(Integer id) {
		booking.setId(id);
		return this;
	}

	public BookingBuilder withBookingNumber(String bookingNumber) {
		booking.setBookingNumber(bookingNumber);
		return this;
	}

	public BookingBuilder withBookingStartDate(Date startDate) {
		booking.setBookingStartDate(startDate);
		return this;
	}

	public BookingBuilder withBookingEndDate(Date endDate) {
		booking.setBookingEndDate(endDate);
		return this;
	}

	public BookingBuilder withBookingStatus(BookingStatus status) {
		booking.setStatus(status);
		return this;
	}




}
