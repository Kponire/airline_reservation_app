package com.oluwaponire.airline_reservation_ticket.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oluwaponire.airline_reservation_ticket.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findByFlightNumber(String flightNumber);

    List<Flight> findByOriginAndDestinationAndDepartureTimeAndTotalSeatsGreaterThanEqual(
        String origin, String destination, Date departureDate, int availableSeats
    );

    List<Flight> findByOriginAndDestinationAndDepartureTimeBetweenAndTotalSeatsGreaterThanEqual(
        String origin, String destination, Date departureDateStart, Date departureDateEnd, int availableSeats
    );


}
