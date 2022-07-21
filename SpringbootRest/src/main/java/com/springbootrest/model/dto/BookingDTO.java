package com.springbootrest.model.dto;


import com.springbootrest.model.enums.BookingStatus;

import java.util.Date;

public class BookingDTO {

	private Integer id;

	private String bookingNumber;

	private Date bookingStartDate;

	private Date bookingEndDate;

	private BookingStatus status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public Date getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(Date bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public Date getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookingDTO{" +
				"id=" + id +
				", bookingNumber='" + bookingNumber + '\'' +
				", bookingStartDate=" + bookingStartDate +
				", bookingEndDate=" + bookingEndDate +
				", status=" + status +
				'}';
	}
}
