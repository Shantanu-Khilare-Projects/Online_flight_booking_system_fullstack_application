package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bookings")
public class Booking extends BaseEntity{

	//@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "booking_status")
	private BookingStatus bookingStatus;
	
	@Column(name = "price")
	private double price;

	@ManyToOne
	@JoinColumn(name = "userId",nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "flight_id",nullable = false)
	private Flight flight;
}
