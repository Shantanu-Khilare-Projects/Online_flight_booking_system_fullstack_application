package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.MultipleSeatsBookingDto;
import com.app.entities.BookingStatus;
import com.app.services.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/bookSingle/{seatId}/{flightId}/{userId}")
	public ResponseEntity<?> bookSingle(@RequestParam Long seatId,
					@RequestParam Long flightId,@RequestParam Long userId) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(bookingService.bookSingleSeat(seatId, userId, flightId));
	}
	@PostMapping("/bookMultiple/{flightId}/{userId}")
	public ResponseEntity<?> bookMultipleSeats(@RequestBody MultipleSeatsBookingDto dto,
						@RequestParam Long flightId,@RequestParam Long userId) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(bookingService.bookMultipleSeats(userId, flightId, dto));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> bookingsByUserId(@RequestParam Long userId) {
		return ResponseEntity.ok(bookingService.bookingsByUserId(userId));
	}
	@GetMapping("/confirmed/{userId}")
	public ResponseEntity<?> confirmedBookingsByUserId(@RequestParam Long userId) {
		return ResponseEntity.ok(bookingService.bookingStatus(BookingStatus.CONFIRMED, userId));
	}
	
	
	
}
