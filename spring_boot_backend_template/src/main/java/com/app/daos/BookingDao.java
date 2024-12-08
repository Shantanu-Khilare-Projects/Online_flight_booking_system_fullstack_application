package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Booking;
import com.app.entities.BookingStatus;

import java.util.List;
import com.app.entities.User;


public interface BookingDao extends JpaRepository<Booking, Long> {

	List<Booking> findByUser(User user);
	List<Booking> findByBookingStatusAndUser(BookingStatus bookingStatus, User user);
}
