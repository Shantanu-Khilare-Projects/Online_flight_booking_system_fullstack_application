package com.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Seat;
import com.app.entities.Flight;
import com.app.entities.SeatClass;



public interface SeatDao extends JpaRepository<Seat, Long> {

	boolean existsBySeatNoAndFlight(String seatNo, Flight flight);
	List<Seat> findByFlightAndAvailable(Flight flight, boolean available);
	List<Seat> findByFlightAndSeatClass(Flight flight, SeatClass seatClass);
	List<Seat> findByFlight(Flight flight);
}
