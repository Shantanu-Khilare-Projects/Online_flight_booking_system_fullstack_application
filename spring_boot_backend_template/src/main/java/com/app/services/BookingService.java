package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.dtos.MultipleSeatsBookingDto;
import com.app.entities.Booking;
import com.app.entities.BookingStatus;

public interface BookingService {

	ApiResponse bookSingleSeat(Long seatId,Long userId,Long flightId);
	ApiResponse bookMultipleSeats(Long userId,Long flightId,MultipleSeatsBookingDto dto);
	List<Booking> bookingsByUserId(Long userId);
	List<Booking> bookingStatus(BookingStatus bookingStatus,Long userId);
}
