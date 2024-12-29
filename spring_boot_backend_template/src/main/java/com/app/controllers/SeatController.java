package com.app.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.SeatDto;
import com.app.dtos.SeatMulDto;
import com.app.services.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class SeatController {
	@Autowired
	private SeatService seatService;

	@PostMapping("/admin/add/{flightId}")
	public ResponseEntity<?> addSeat(@RequestBody SeatDto dto,@PathVariable Long flightId) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
								.body(seatService.addSingleSeat(dto, flightId));
	}
	
	@PostMapping("/admin/addAll/{flightId}")
	public ResponseEntity<?> addMultipleSeats(@PathVariable Long flightId, @RequestBody SeatMulDto dto) {	
		return ResponseEntity.status(HttpStatus.CREATED)
								.body(seatService.addMultipleSeats(flightId, dto));
	}
	@GetMapping("/all/seat/{flightId}")
	public ResponseEntity<?> getSeatsByFlight(@PathVariable Long flightId) {
		return ResponseEntity.ok(seatService.flightSeats(flightId));
	}
	@GetMapping("/all/seat/available/{flightId}")
	public ResponseEntity<?> availableSeats(@RequestParam Long flightId) {
		return ResponseEntity.ok(seatService.availableSeats(flightId));
	}
	@GetMapping("/all/seat/business/{flightId}")
	public ResponseEntity<?> businessSeats(@RequestParam Long flightId) {
		return ResponseEntity.ok(seatService.businessSeats(flightId));
	}
	@GetMapping("/all/seat/economy/{flightId}")
	public ResponseEntity<?> economySeats(@RequestParam Long flightId) {
		return ResponseEntity.ok(seatService.economySeats(flightId));
	}
	
}
