package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.ReservationRequestDto;
import com.oluwaponire.airline_reservation_ticket.dto.SearchFlightRequestDto;
import com.oluwaponire.airline_reservation_ticket.entity.Flight;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.repository.FlightRepository;
import com.oluwaponire.airline_reservation_ticket.repository.ReservationRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void bookReservation(ReservationRequestDto reservationRequestDto) {
        User user = userRepository.findByUsername(reservationRequestDto.getUsername());
        Flight flight = flightRepository.findByFlightNumber(reservationRequestDto.getFlight());
        if (user == null) {
            throw new RuntimeException("Invalid user response");
        }
        if (flight == null) {
            throw new RuntimeException("Invalid flight response");
        }
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setUser(user);
        reservation.setFromDestination(reservationRequestDto.getFromDestination());
        reservation.setToDestination(reservationRequestDto.getToDestination());
        reservation.setDepartDate(reservationRequestDto.getDepartDate());
        reservation.setReturnDate(reservationRequestDto.getReturnDate());
        reservation.setTravelClass(reservationRequestDto.getTravelClass());
        reservation.setNumberOfPassengers(reservationRequestDto.getNumberOfPassengers());
        double totalPrice = calculateTotalPrice(flight, reservationRequestDto.getTravelClass(), reservationRequestDto.getNumberOfPassengers());
        reservation.setTotalPrice(totalPrice);
        // Assign seats
        String seatNumbers = assignSeats(flight, reservationRequestDto.getNumberOfPassengers());
        reservation.setSeatNumbers(seatNumbers);
        reservation.setConfirmed(true);
        reservationRepository.save(reservation);
    }

    private String assignSeats(Flight flight, int numberOfPassengers) {
        StringBuilder seatNumbers = new StringBuilder();
        for (int i = 0; i < numberOfPassengers; i++) {
            if (i > 0) seatNumbers.append(",");
            seatNumbers.append("seat" + (i + 1));
        }
        return seatNumbers.toString();
    }

    private double calculateTotalPrice(Flight flight, String travelClass, int numberOfPassengers) {
        double basePrice = flight.getBasePrice();
        double classMultiplier = getClassMultiplier(travelClass);
        return basePrice * classMultiplier * numberOfPassengers;
    }

    private double getClassMultiplier(String travelClass) {
        switch (travelClass) {
            case "business":
                return 1.5;
            case "first":
                return 2.0;
            default:
                return 1.0;
        }    
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        throw new UnsupportedOperationException("Unimplemented method 'getReservationById'");
    }

    @Override
    public boolean updateReservation(Long reservationId, ReservationRequestDto reservationRequestDto) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setFromDestination(reservationRequestDto.getFromDestination());
            reservation.setToDestination(reservationRequestDto.getToDestination());
            reservation.setDepartDate(reservationRequestDto.getDepartDate());
            reservation.setReturnDate(reservationRequestDto.getReturnDate());
            reservation.setTravelClass(reservationRequestDto.getTravelClass());
            reservation.setNumberOfPassengers(reservationRequestDto.getNumberOfPassengers());
            // Recalculate total price
            double totalPrice = calculateTotalPrice(reservation.getFlight(), reservationRequestDto.getTravelClass(), reservationRequestDto.getNumberOfPassengers());
            reservation.setTotalPrice(totalPrice);
            // Reassign seats if necessary
            String seatNumbers = assignSeats(reservation.getFlight(), reservationRequestDto.getNumberOfPassengers());
            reservation.setSeatNumbers(seatNumbers);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelReservation(Long reservationId) {
        Optional<Reservation> optionalReservation  = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservationRepository.delete(reservation);
            return true;
        }
        return false;
    }

    @Override
    public List<Flight> searchFlights(SearchFlightRequestDto searchFlightRequestDto) {
        switch (searchFlightRequestDto.getTripType().toUpperCase()) {
            case "ONE_WAY":
                return flightRepository.findByOriginAndDestinationAndDepartureTimeAndTotalSeatsGreaterThanEqual(
                        searchFlightRequestDto.getFromDestination(),
                        searchFlightRequestDto.getToDestination(),
                        searchFlightRequestDto.getDepartureDate(),
                        searchFlightRequestDto.getNumberOfPassengers()
                    );
            case "ROUND_TRIP":
                List<Flight> outboundFlights = flightRepository.findByOriginAndDestinationAndDepartureTimeAndTotalSeatsGreaterThanEqual(
                        searchFlightRequestDto.getFromDestination(),
                        searchFlightRequestDto.getToDestination(),
                        searchFlightRequestDto.getDepartureDate(),
                        searchFlightRequestDto.getNumberOfPassengers()
                    );
                List<Flight> returnFlights = flightRepository.findByOriginAndDestinationAndDepartureTimeAndTotalSeatsGreaterThanEqual(
                        searchFlightRequestDto.getFromDestination(),
                        searchFlightRequestDto.getToDestination(),
                        searchFlightRequestDto.getReturnDate(),
                        searchFlightRequestDto.getNumberOfPassengers()
                    );
                returnFlights.addAll(outboundFlights);
                return returnFlights;
            case "MULTI_DIMENSIONAL":
                return searchFlightRequestDto.getLegs().stream()
                        .flatMap(leg -> flightRepository.findByOriginAndDestinationAndDepartureTimeBetweenAndTotalSeatsGreaterThanEqual(
                                 leg.getFromDestination(),
                                 leg.getToDestination(),
                                 leg.getDepartureDate(),
                                 searchFlightRequestDto.getDepartureDate(),
                                 searchFlightRequestDto.getNumberOfPassengers()
                        ).stream())
                        .collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Invalid trip type: " + searchFlightRequestDto.getTripType());
        }    
    }

}
