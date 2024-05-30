package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;

import com.oluwaponire.airline_reservation_ticket.dto.ReservationRequestDto;
import com.oluwaponire.airline_reservation_ticket.dto.SearchFlightRequestDto;
import com.oluwaponire.airline_reservation_ticket.entity.Flight;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;

public interface ReservationService {

    void bookReservation(ReservationRequestDto reservationRequestDto);

    Reservation getReservationById(Long reservationId);

    boolean updateReservation(Long reservationId, ReservationRequestDto reservationRequestDto);

    boolean cancelReservation(Long reservationId);

    List<Flight> searchFlights(SearchFlightRequestDto searchFlightRequestDto);

}
