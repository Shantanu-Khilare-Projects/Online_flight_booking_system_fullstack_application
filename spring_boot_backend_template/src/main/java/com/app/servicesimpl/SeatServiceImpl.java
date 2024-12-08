package com.app.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.FlightDao;
import com.app.daos.SeatDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.SeatDto;
import com.app.dtos.SeatMulDto;
import com.app.entities.Flight;
import com.app.entities.Seat;
import com.app.entities.SeatClass;
import com.app.services.SeatService;

@Service
@Transactional
public class SeatServiceImpl implements SeatService{
	@Autowired
	private SeatDao seatDao;
	
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addSingleSeat(SeatDto dto,Long flightId) {
		Flight flight =flightDao.findById(flightId)
								.orElseThrow(()->new RuntimeException("Invalid Flight id..."));
		
		if(seatDao.existsBySeatNoAndFlight(dto.getSeatNo(), flight))
			return new ApiResponse("Seat number of this flight already exists...");
		
		Seat seat=mapper.map(dto, Seat.class);
		seat.setFlight(flight);
		seatDao.save(seat);
		return new ApiResponse("Seat added successfully!!!");
	}

	@Override
	public ApiResponse addMultipleSeats(Long flightId, SeatMulDto dto) {
		Flight flight =flightDao.findById(flightId)
				.orElseThrow(()->new RuntimeException("Invalid Flight id..."));
		
		List<Seat> economySeatList=new ArrayList<>();
		List<Seat> businessSeatList=new ArrayList<>();
		
		char c='A';
		for(int i=0;i<dto.getBusinessColumns();i++) {
			for(int j=1;j<=dto.getBusinessRows();j++) {
				String seatNo=""+j+c;
				Seat seatDto=new Seat();
				seatDto.setSeatNo(seatNo);
				seatDto.setSeatClass(SeatClass.BUSINESS);
				seatDto.setFlight(flight);
				businessSeatList.add(seatDto);
			}
			c++;
		}
		c='A';
		for(int i=0;i<dto.getEconomyColumns();i++) {
			for(int j=1;j<=dto.getEconomyRows();j++) {
				String seatNo=""+j+c;
				Seat seatDto=new Seat();
				seatDto.setSeatNo(seatNo);
				seatDto.setSeatClass(SeatClass.ECONOMY);
				seatDto.setFlight(flight);
				economySeatList.add(seatDto);
			}
			c++;
		}
		seatDao.saveAll(businessSeatList);
		seatDao.saveAll(economySeatList);
	
		return new ApiResponse("seats added successfully!!!");
	}

	@Override
	public List<Seat> availableSeats(Long flightId) {
		Flight flight =flightDao.findById(flightId)
				.orElseThrow(()->new RuntimeException("Invalid Flight id..."));
		return seatDao.findByFlightAndAvailable(flight, true);
	}

	@Override
	public List<Seat> businessSeats(Long flightId) {
		Flight flight =flightDao.findById(flightId)
				.orElseThrow(()->new RuntimeException("Invalid Flight id..."));
		return seatDao.findByFlightAndSeatClass(flight, SeatClass.BUSINESS);
	}

	@Override
	public List<Seat> economySeats(Long flightId) {
		Flight flight =flightDao.findById(flightId)
				.orElseThrow(()->new RuntimeException("Invalid Flight id..."));
		return seatDao.findByFlightAndSeatClass(flight, SeatClass.ECONOMY);
	}

	@Override
	public List<Seat> flightSeats(Long flightId) {
		Flight flight =flightDao.findById(flightId)
				.orElseThrow(()->new RuntimeException("Invalid Flight id..."));
		return seatDao.findByFlight(flight);
	}

}
