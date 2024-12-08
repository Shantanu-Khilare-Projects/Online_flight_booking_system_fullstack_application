package com.app.servicesimpl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.FlightDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.FlightOriginDestinationDto;
import com.app.dtos.FlightReqDto;
import com.app.entities.Flight;
import com.app.entities.Status;
import com.app.services.FlightService;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private FlightDao flightDao;

	@Override
	public ApiResponse addFlight(FlightReqDto dto) {
		Flight flight=mapper.map(dto, Flight.class);
		if(dto.getArrivalTime().compareTo(dto.getDepartureTime())<=0) {
			return new ApiResponse("Arrival time cannot be before departure time...");
		}
		flight.setAvailableSeats(dto.getTotalSeats());
		flightDao.save(flight);
		return new ApiResponse("Flight added successfully");		
	}

	@Override
	public List<Flight> showFlights(FlightOriginDestinationDto dto) {
		
		return flightDao.findByOriginAndDestination(dto.getOrigin(),dto.getDestination());
	}

	@Override
	public List<Flight> showFlightsByDepartureTime(LocalDateTime departureTime) {
		return flightDao.findByDepartureTime(departureTime);
	}

	@Override
	public List<Flight> showFlightsByarrivalTime(LocalDateTime arrivalTime) {
		return flightDao.findByArrivalTime(arrivalTime);
	}

	@Override
	public List<Flight> showFlightsByStatus(Status status) {
		return flightDao.findByStatus(status);
	}

	@Override
	public List<Flight> showFlightsByAvailableSeats(int availableSeats) {
		return flightDao.findByAvailableSeatsGreaterThanEqual(availableSeats);
	}

	@Override
	public List<Flight> showFlightsByDestination(String destination) {
		return flightDao.findByDestination(destination);
	}
	
}
