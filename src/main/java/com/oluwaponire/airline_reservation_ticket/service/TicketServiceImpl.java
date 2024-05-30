package com.oluwaponire.airline_reservation_ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.TicketDto;
import com.oluwaponire.airline_reservation_ticket.entity.Flight;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.entity.Ticket;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.exception.ResourceNotFoundException;
import com.oluwaponire.airline_reservation_ticket.repository.FlightRepository;
import com.oluwaponire.airline_reservation_ticket.repository.ReservationRepository;
import com.oluwaponire.airline_reservation_ticket.repository.TicketRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();

        Flight flight = flightRepository.findById(ticketDto.getFlightId())
            .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        Reservation reservation = reservationRepository.findById(ticketDto.getReservationId())
            .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        User user = userRepository.findById(ticketDto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ticket.setTicketNumber(ticketDto.getTicketNumber());
        ticket.setFlight(flight);
        ticket.setReservation(reservation);
        ticket.setUser(user);
        ticket.setSeatNumber(reservation.getSeatNumbers());
        ticket.setTicketStatus(ticketDto.getTicketStatus());
        ticket.setBookingDate(ticketDto.getBookingDate());
        ticket.setDepartureDate(ticketDto.getDepartureDate());
        ticket.setArrivalDate(ticketDto.getArrivalDate());

        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDto(savedTicket);
    }

    @Override
    public TicketDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        return convertToDto(ticket);
    }

    @Override
    public TicketDto updateTicket(Long id, TicketDto ticketDto) {
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        Flight flight = flightRepository.findById(ticketDto.getFlightId())
            .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        Reservation reservation = reservationRepository.findById(ticketDto.getReservationId())
            .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));

        User user = userRepository.findById(ticketDto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ticket.setTicketNumber(ticketDto.getTicketNumber());
        ticket.setFlight(flight);
        ticket.setReservation(reservation);
        ticket.setUser(user);
        ticket.setSeatNumber(ticketDto.getSeatNumber());
        ticket.setTicketStatus(ticketDto.getTicketStatus());
        ticket.setBookingDate(ticketDto.getBookingDate());
        ticket.setDepartureDate(ticketDto.getDepartureDate());
        ticket.setArrivalDate(ticketDto.getArrivalDate());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToDto(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        ticketRepository.delete(ticket);
    }

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setTicketNumber(ticket.getTicketNumber());
        ticketDto.setFlightId(ticket.getFlight().getId());
        ticketDto.setReservationId(ticket.getReservation().getId());
        ticketDto.setUserId(ticket.getUser().getId());
        ticketDto.setSeatNumber(ticket.getSeatNumber());
        ticketDto.setTicketStatus(ticket.getTicketStatus());
        ticketDto.setBookingDate(ticket.getBookingDate());
        ticketDto.setDepartureDate(ticket.getDepartureDate());
        ticketDto.setArrivalDate(ticket.getArrivalDate());
        return ticketDto;
    }

}
