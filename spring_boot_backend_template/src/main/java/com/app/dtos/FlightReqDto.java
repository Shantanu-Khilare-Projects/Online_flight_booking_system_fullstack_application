package com.app.dtos;

import java.time.LocalDateTime;

import com.app.entities.Status;

import lombok.Data;

@Data
public class FlightReqDto {

	private String flightNumber;
	private String airline;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private Status status;
	private int totalSeats;
	private double economyRate;
	private double businessRate;
}
