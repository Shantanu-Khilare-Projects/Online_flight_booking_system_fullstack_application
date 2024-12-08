package com.app.servicesimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.BookingDao;
import com.app.daos.FlightDao;
import com.app.daos.SeatDao;
import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.MultipleSeatsBookingDto;
import com.app.entities.Booking;
import com.app.entities.BookingStatus;
import com.app.entities.Flight;
import com.app.entities.Seat;
import com.app.entities.SeatClass;
import com.app.entities.User;
import com.app.services.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FlightDao flightDao;
	
	@Autowired
	private SeatDao seatDao;
	
	@Autowired
	private BookingDao bookingDao;

	@Override
	public ApiResponse bookSingleSeat(Long seatId, Long userId, Long flightId) {
		User user=userDao.findById(userId)
							.orElseThrow(()->new RuntimeException("User id not found"));
		
		Flight flight=flightDao.findById(flightId)
							.orElseThrow(()->new RuntimeException("Flight id not found"));
		
		Seat seat=seatDao.findById(seatId)
							.orElseThrow(()->new RuntimeException("Seat id not found"));
		
		if(seat.isAvailable()==false)
			return new ApiResponse("Seat is not available...");
			
		
		Booking booking=new Booking();
			

		if(seat.getSeatClass()==SeatClass.ECONOMY)
			booking.setPrice(flight.getEconomyRate());
		else
			booking.setPrice(flight.getBusinessRate());
		
		booking.setFlight(flight);
		booking.setUser(user);
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		booking.setQuantity(1);
		
		flight.setAvailableSeats(flight.getAvailableSeats()-1);
		seat.setAvailable(false);
		
		flightDao.save(flight);
		seatDao.save(seat);
		bookingDao.save(booking);
		
		return new ApiResponse("Booking confirmed!!!");
	}


	@Override
	public ApiResponse bookMultipleSeats(Long userId, Long flightId, MultipleSeatsBookingDto dto) {
		User user=userDao.findById(userId)
				.orElseThrow(()->new RuntimeException("User id not found"));

		Flight flight=flightDao.findById(flightId)
				.orElseThrow(()->new RuntimeException("Flight id not found"));

		Booking booking=new Booking();
		booking.setFlight(flight);
		booking.setUser(user);
		booking.setQuantity(dto.getSeatIdList().size());
//		booking.setPrice(dto.getTotalPrice());
		
		flight.setAvailableSeats(flight.getAvailableSeats()-dto.getSeatIdList().size());
		Set<Seat> seatList=new HashSet<>();
		double totalPrice=0;
		
		for(Long seatId:dto.getSeatIdList()) {
			Seat seat=seatDao.findById(seatId)
					.orElseThrow(()->new RuntimeException("Seat id not found"));
			seat.setAvailable(false);
			seatList.add(seat);
			if(seat.getSeatClass()==SeatClass.ECONOMY)
				totalPrice+=flight.getEconomyRate();
			else
				totalPrice+=flight.getBusinessRate();
		}
		booking.setPrice(totalPrice);
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		
		flightDao.save(flight);
		seatDao.saveAll(seatList);
		bookingDao.save(booking);
		
		return new ApiResponse("Booking confirmed!!!");
	}


	@Override
	public List<Booking> bookingsByUserId(Long userId) {
		User user=userDao.findById(userId)
				.orElseThrow(()->new RuntimeException("User id not found"));
		
		return bookingDao.findByUser(user);
	}


	@Override
	public List<Booking> bookingStatus(BookingStatus bookingStatus, Long userId) {
		User user=userDao.findById(userId)
				.orElseThrow(()->new RuntimeException("User id not found"));
		
		return bookingDao.findByBookingStatusAndUser(bookingStatus.CONFIRMED, user);
	}

}
