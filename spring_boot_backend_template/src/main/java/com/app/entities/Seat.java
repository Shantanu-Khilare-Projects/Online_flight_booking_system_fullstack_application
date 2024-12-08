package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seat extends BaseEntity {

	@Column(name = "seat_no", nullable = false)
	private String seatNo;

	private SeatClass seatClass;

	@Column(nullable = false)
	private boolean available = true;
	
	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;
	
	
}