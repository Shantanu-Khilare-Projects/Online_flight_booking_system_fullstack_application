package com.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "flights")
public class Flight extends BaseEntity{

	@Column(name = "flight_number",unique = true,nullable = false,length = 50)
	private String flightNumber;
	
	@Column(name = "airline",nullable = false)
	private String airline;
	
	@Column(name = "origin",nullable = false)
	private String origin;

	@Column(name = "destination",nullable = false)
	private String destination;
	
	private LocalDateTime departureTime;
	
	private LocalDateTime arrivalTime;
	
	private int availableSeats;
	
	private int totalSeats;
	
	private double economyRate;
	
	private double businessRate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
}
