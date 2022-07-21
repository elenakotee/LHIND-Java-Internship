package com.springbootrest.model.builder;

import com.springbootrest.model.dto.BookingDTO;
import com.springbootrest.model.enums.BookingStatus;

import java.util.Date;

public class BookingDTOBuilder {

	private BookingDTO bookingDTO = new BookingDTO();

	public BookingDTO build() {
		return bookingDTO;
	}

	public BookingDTOBuilder withId(Integer id) {
		bookingDTO.setId(id);
		return this;
	}

	public BookingDTOBuilder withBookingNumber(String bookingNumber) {
		bookingDTO.setBookingNumber(bookingNumber);
		return this;
	}

	public BookingDTOBuilder withBookingStartDate(Date startDate) {
		bookingDTO.setBookingStartDate(startDate);
		return this;
	}

	public BookingDTOBuilder withBookingEndDate(Date endDate) {
		bookingDTO.setBookingEndDate(endDate);
		return this;
	}

	public BookingDTOBuilder withBookingStatus(BookingStatus status) {
		bookingDTO.setStatus(status);
		return this;
	}


}
