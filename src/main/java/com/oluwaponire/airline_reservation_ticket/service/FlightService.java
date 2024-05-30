package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;

import com.oluwaponire.airline_reservation_ticket.dto.FlightDto;
import com.oluwaponire.airline_reservation_ticket.entity.Flight;

public interface FlightService {

    Flight addFlight(FlightDto flightDto);

    Flight updateFlight(Long id, FlightDto flightDto);

    void deleteFlight(Long id);

    Flight getFlightById(Long id);

    List<Flight> getAllFlights();

}
