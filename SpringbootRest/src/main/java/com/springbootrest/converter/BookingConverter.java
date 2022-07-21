package com.springbootrest.converter;

import com.springbootrest.model.builder.BookingBuilder;
import com.springbootrest.model.builder.BookingDTOBuilder;
import com.springbootrest.model.dto.BookingDTO;
import com.springbootrest.model.entity.Booking;

public class BookingConverter extends AbstractConverter<Booking, BookingDTO>{
	@Override
	public Booking toEntity(BookingDTO bookingDTO) {
		return new BookingBuilder().
				withId(bookingDTO.getId())
				.withBookingNumber(bookingDTO.getBookingNumber())
				.withBookingStartDate(bookingDTO.getBookingStartDate())
				.withBookingEndDate(bookingDTO.getBookingEndDate())
				.withBookingStatus(bookingDTO.getStatus())
				.build();
	}

	@Override
	public BookingDTO toDTO(Booking booking) {
		return new BookingDTOBuilder()
				.withId(booking.getId())
				.withBookingNumber(booking.getBookingNumber())
				.withBookingStartDate(booking.getBookingStartDate())
				.withBookingEndDate(booking.getBookingEndDate())
				.withBookingStatus(booking.getStatus())
				.build();
	}
}
