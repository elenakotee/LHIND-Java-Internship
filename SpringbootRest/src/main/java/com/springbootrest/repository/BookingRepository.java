package com.springbootrest.repository;

import com.springbootrest.model.entity.Booking;
import com.springbootrest.model.entity.Employee;
import com.springbootrest.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

	Booking findByBookingNumber(String bookingNumber);

	List<Booking> findByEmployee(Employee employee);

	List<Booking> findByBookingStartDate(Date startDate);

	List<Booking> findByBookingEndDate(Date endDate);

	List<Booking> findByStatus(BookingStatus status);

}
