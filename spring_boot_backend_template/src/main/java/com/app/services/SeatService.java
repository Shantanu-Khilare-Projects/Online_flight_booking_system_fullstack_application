package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.dtos.SeatDto;
import com.app.dtos.SeatMulDto;
import com.app.entities.Seat;

public interface SeatService {

	ApiResponse addSingleSeat(SeatDto dto, Long flightId);

	ApiResponse addMultipleSeats(Long flightId, SeatMulDto dto);

	List<Seat> availableSeats(Long flightId);

	List<Seat> businessSeats(Long flightId);

	List<Seat> economySeats(Long flightId);

	List<Seat> flightSeats(Long flightId);
}
