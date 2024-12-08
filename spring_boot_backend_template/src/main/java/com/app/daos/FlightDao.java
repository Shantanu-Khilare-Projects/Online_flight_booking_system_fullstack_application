package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Flight;
import java.time.LocalDateTime;
import com.app.entities.Status;

public interface FlightDao extends JpaRepository<Flight, Long> {

	List<Flight> findByOriginAndDestination(String origin, String destination);

	List<Flight> findByAirline(String airline);

	List<Flight> findByDepartureTime(LocalDateTime departureTime);

	List<Flight> findByArrivalTime(LocalDateTime arrivalTime);

	List<Flight> findByStatus(Status status);

	List<Flight> findByAvailableSeats(int availableSeats);
	
	List<Flight> findByAvailableSeatsGreaterThanEqual(int availableSeats);

	List<Flight> findByDestination(String destination);

}
