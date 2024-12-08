package com.app.services;

import java.time.LocalDateTime;
import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.dtos.FlightOriginDestinationDto;
import com.app.dtos.FlightReqDto;
import com.app.entities.Flight;
import com.app.entities.Status;

public interface FlightService {

	ApiResponse addFlight(FlightReqDto dto);
	List<Flight> showFlights(FlightOriginDestinationDto dto);
	List<Flight> showFlightsByDepartureTime(LocalDateTime departureTime);
	List<Flight> showFlightsByarrivalTime(LocalDateTime arrivalTime);
	List<Flight> showFlightsByStatus(Status status);
	List<Flight> showFlightsByAvailableSeats(int availableSeats);
	List<Flight> showFlightsByDestination(String destination);
}
