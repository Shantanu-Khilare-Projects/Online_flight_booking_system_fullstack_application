package com.app.dtos;

import com.app.entities.SeatClass;

import lombok.Data;

@Data
public class SeatDto {

	private String seatNo;
	private SeatClass seatClass;
	private boolean available;
	
	
}
