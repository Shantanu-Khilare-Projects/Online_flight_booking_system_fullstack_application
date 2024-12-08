package com.app.dtos;

import com.app.entities.SeatClass;

import lombok.Data;

@Data
public class SeatMulDto {

	private int economyRows;
	private int economyColumns;
	private int businessRows;
	private int businessColumns;
}
