package com.app.dtos;

import java.util.List;

import lombok.Data;

@Data
public class MultipleSeatsBookingDto {
	private List<Long> seatIdList;
}
