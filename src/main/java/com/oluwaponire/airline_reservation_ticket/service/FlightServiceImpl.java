package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.FlightDto;
import com.oluwaponire.airline_reservation_ticket.entity.Flight;
import com.oluwaponire.airline_reservation_ticket.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight addFlight(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setAirline(flightDto.getAirline());
        flight.setOrigin(flightDto.getOrigin());
        flight.setDestination(flightDto.getDestination());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setTotalSeats(flightDto.getTotalSeats());
        flight.setBasePrice(flightDto.getBasePrice());
        return flightRepository.save(flight);    
    }

    @Override
    public Flight updateFlight(Long id, FlightDto flightDto) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            flight.setFlightNumber(flightDto.getFlightNumber());
            flight.setAirline(flightDto.getAirline());
            flight.setOrigin(flightDto.getOrigin());
            flight.setDestination(flightDto.getDestination());
            flight.setDepartureTime(flightDto.getDepartureTime());
            flight.setArrivalTime(flightDto.getArrivalTime());
            flight.setTotalSeats(flightDto.getTotalSeats());
            flight.setBasePrice(flightDto.getBasePrice());
            return flightRepository.save(flight);       
        } else {
            throw new RuntimeException("Flight not found");
        }
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

}
