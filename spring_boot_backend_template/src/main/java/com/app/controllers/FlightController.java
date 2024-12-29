package com.app.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.FlightOriginDestinationDto;
import com.app.dtos.FlightReqDto;
import com.app.entities.Flight;
import com.app.entities.Status;
import com.app.services.FlightService;


@RestController
public class FlightController {
	@Autowired
	private FlightService flightService;

	@PostMapping("/admin/flight/add")
	public ResponseEntity<?> addNewFlight(@RequestBody FlightReqDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(flightService.addFlight(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new RuntimeException("something went wrong..."));
		}
	}
	
	@GetMapping("/all/fromto")
	public ResponseEntity<?> getFlightsByOriginAndDestination(@RequestBody FlightOriginDestinationDto dto) {
		return ResponseEntity.ok(flightService.showFlights(dto));
	}
	
	@GetMapping("/all/departureTime/{departureTime}")
	public List<Flight> showFlightsByDepartureTime(@RequestParam LocalDateTime departureTime) {
		return flightService.showFlightsByDepartureTime(departureTime);
	}

	@GetMapping("/all/arrivalTime/{arrivalTime}")
	public List<Flight> showFlightsByarrivalTime(@RequestParam LocalDateTime arrivalTime) {
		return flightService.showFlightsByarrivalTime(arrivalTime);
	}

	@GetMapping("/all/status/{status}")
	public List<Flight> showFlightsByStatus(@RequestParam Status status) {
		return flightService.showFlightsByStatus(status);
	}

	@GetMapping("/all/availableSeats/{availableSeats}")
	public List<Flight> showFlightsByAvailableSeats(@RequestParam int availableSeats) {
		return flightService.showFlightsByAvailableSeats(availableSeats);
	}

	@GetMapping("/all/destination/{destination}")
	public List<Flight> showFlightsByDestination(@RequestParam String destination) {
		return flightService.showFlightsByDestination(destination);
	}
	

}
